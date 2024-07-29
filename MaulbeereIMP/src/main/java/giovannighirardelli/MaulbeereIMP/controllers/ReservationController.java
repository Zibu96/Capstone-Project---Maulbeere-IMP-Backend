package giovannighirardelli.MaulbeereIMP.controllers;


import giovannighirardelli.MaulbeereIMP.entities.Reservation;
import giovannighirardelli.MaulbeereIMP.exceptions.BadRequestException;
import giovannighirardelli.MaulbeereIMP.payloads.ReservationDTO.ReservationDTO;
import giovannighirardelli.MaulbeereIMP.payloads.ReservationDTO.ReservationResponseDTO;
import giovannighirardelli.MaulbeereIMP.payloads.UsersDTO.UserResponseDTO;
import giovannighirardelli.MaulbeereIMP.servicies.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;


    @GetMapping
    public Page<Reservation> getReservation(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "id") String sortBy) {

        return this.reservationService.getAllReservations(page, size, sortBy);
    }

    @GetMapping("/today")
    public Page<Reservation> getTodayReservations(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(defaultValue = "id") String sortBy) {

        return this.reservationService.getAllTodayReservations(page, size, sortBy);
    }

    @PostMapping
    public ReservationResponseDTO saveReservation(@RequestBody @Validated ReservationDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        System.out.println(body);
        return new ReservationResponseDTO(this.reservationService.saveReservation(body).getId());

    }

    @PutMapping("/{reservationId}")
    public Reservation findByIdAndUpdate(@PathVariable UUID reservationId, @RequestBody ReservationDTO body){
        return this.reservationService.findByIdAndUpdate(reservationId, body);

    }

    @DeleteMapping("/{reservationId}")
    public void deleteReservation(@PathVariable UUID reservationId) {
        reservationService.findReservationByIdAndDelete(reservationId);
    }
}
