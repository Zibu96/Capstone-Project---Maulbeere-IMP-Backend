package giovannighirardelli.MaulbeereIMP.controllers;

import giovannighirardelli.MaulbeereIMP.entities.Event;
import giovannighirardelli.MaulbeereIMP.exceptions.BadRequestException;
import giovannighirardelli.MaulbeereIMP.payloads.EventDTO.EventDTO;
import giovannighirardelli.MaulbeereIMP.payloads.EventDTO.EventResponseDTO;
import giovannighirardelli.MaulbeereIMP.servicies.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;


    @GetMapping
    public Page<Event> getEvent(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(defaultValue = "id") String sortBy) {

        return this.eventService.getAllEvents(page, size, sortBy);
    }

    @PostMapping
    public EventResponseDTO saveEvent(@RequestBody @Validated EventDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        System.out.println(body);
        return new EventResponseDTO(this.eventService.saveEvent(body).getId());

    }

    @GetMapping("/{eventId}")
    public Event findById(@PathVariable UUID eventId) {
        return this.eventService.findById(eventId);
    }

    @PutMapping("/{eventId}")
    public Event findByIdAndUpdate(@PathVariable UUID eventId, @RequestBody EventDTO body){
        return this.eventService.findByIdAndUpdate(eventId, body);

    }


    @DeleteMapping("/{eventId}")
    public void deleteEvent(@PathVariable UUID eventId) {
        eventService.findEventByIdAndDelete(eventId);
    }

    @GetMapping("/dates/{date}")
    public List<Event> findByDate(@PathVariable LocalDate date) {
        return this.eventService.findByDate(date);
    }
}
