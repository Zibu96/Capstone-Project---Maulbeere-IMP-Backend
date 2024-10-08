package giovannighirardelli.MaulbeereIMP.servicies;


import giovannighirardelli.MaulbeereIMP.entities.Reservation;
import giovannighirardelli.MaulbeereIMP.entities.User;
import giovannighirardelli.MaulbeereIMP.enums.EventType;
import giovannighirardelli.MaulbeereIMP.enums.ReservationType;
import giovannighirardelli.MaulbeereIMP.exceptions.BadRequestException;
import giovannighirardelli.MaulbeereIMP.exceptions.NotFoundException;
import giovannighirardelli.MaulbeereIMP.payloads.ReservationDTO.ReservationDTO;
import giovannighirardelli.MaulbeereIMP.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserService userService;


    public Page<Reservation> getAllReservations(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return reservationRepository.findAll(pageable);
    }

    public Reservation saveReservation(ReservationDTO body) {
       User found = this.userService.findById(body.user());

        if (body.date().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data della prenotazione non può essere precedente a oggi.");
        }

        Reservation reservation = new Reservation(body.name(), body.surname(), body.seats(), body.telephone(), body.date(), body.time(), body.specialRequest(), convertStringToEventType(body.eventType()), convertStringToReservationType(body.reservationType()), found );

        return reservationRepository.save(reservation);
    }

    private static EventType convertStringToEventType (String event){
        if (event == null) {
            return null;
        }
        try{
            return EventType.valueOf(event.toUpperCase());
        }catch (IllegalArgumentException e) {
            throw new BadRequestException("The selected event type don't exists");
        }
    }

    public Reservation findById(UUID id) {
        return this.reservationRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public List<Reservation> findByDate(LocalDate date) {
        return this.reservationRepository.getByDate(date);
    }

    private static ReservationType convertStringToReservationType (String resType){
        if (resType == null) {
            return null;
        }
        try{
            return ReservationType.valueOf(resType.toUpperCase());
        }catch (IllegalArgumentException e) {
            throw new BadRequestException("The selected reservation type don't exists");
        }
    }

    public Page<Reservation> getAllTodayReservations(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return reservationRepository.findByDate(LocalDate.now(),pageable);
    }

    public Reservation findByIdAndUpdate(UUID id, ReservationDTO payload) {
        Reservation found = this.findById(id);
        User user = this.userService.findById(payload.user());

        if (payload.date().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data della prenotazione non può essere precedente a oggi.");
        }
        found.setName(payload.name());
        found.setSurname(payload.surname());
        found.setSeats(payload.seats());
        found.setTelephone(payload.telephone());
        found.setDate(payload.date());
        found.setTime(payload.time());
        found.setSpecialRequest(payload.specialRequest());
        found.setEventType(convertStringToEventType(payload.eventType()));
        found.setReservationType(convertStringToReservationType(payload.reservationType()));
        found.setUser(user);
        return reservationRepository.save(found);
    }

    public void findReservationByIdAndDelete(UUID id) {
        Reservation found = this.findById(id);
        this.reservationRepository.delete(found);
    }

}
