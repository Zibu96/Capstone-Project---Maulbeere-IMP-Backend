package giovannighirardelli.MaulbeereIMP.servicies;


import giovannighirardelli.MaulbeereIMP.entities.Event;
import giovannighirardelli.MaulbeereIMP.enums.EventType;
import giovannighirardelli.MaulbeereIMP.exceptions.BadRequestException;
import giovannighirardelli.MaulbeereIMP.exceptions.NotFoundException;
import giovannighirardelli.MaulbeereIMP.payloads.EventDTO.EventDTO;
import giovannighirardelli.MaulbeereIMP.repositories.EventRepository;
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
public class EventService {
    @Autowired
    private EventRepository eventRepository;


    public Page<Event> getAllEvents(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return eventRepository.findAll(pageable);
    }

    public Event saveEvent(EventDTO body) {


        Event event = new Event(body.name(), body.description(), convertStringToEventType(body.eventType()), body.date(), body.time());

        return eventRepository.save(event);
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

    public Event findById(UUID id) {
        return this.eventRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public List<Event> findByDate(LocalDate date) {
        return this.eventRepository.getByDate(date);
    }

    public Event findByIdAndUpdate(UUID id, EventDTO payload) {
        Event found = this.findById(id);

        found.setName(payload.name());
        found.setDescription(payload.description());;
        found.setDate(payload.date());
        found.setTime(payload.time());
        found.setEventType(convertStringToEventType(payload.eventType()));

        return eventRepository.save(found);
    }

    public void findEventByIdAndDelete(UUID id) {
        Event found = this.findById(id);
        this.eventRepository.delete(found);
    }
}
