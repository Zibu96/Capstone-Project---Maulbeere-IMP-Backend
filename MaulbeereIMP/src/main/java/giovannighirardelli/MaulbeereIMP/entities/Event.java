package giovannighirardelli.MaulbeereIMP.entities;

import giovannighirardelli.MaulbeereIMP.enums.EventType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "event")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    private LocalDate date;
    private LocalTime time;


    public Event(String name, String description, EventType eventType, LocalDate date, LocalTime time) {
        this.name = name;
        this.description = description;
        this.eventType = eventType;
        this.date = date;
        this.time = time;
    }
}
