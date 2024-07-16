package giovannighirardelli.MaulbeereIMP.entities;

import giovannighirardelli.MaulbeereIMP.enums.EventType;
import giovannighirardelli.MaulbeereIMP.enums.ReservationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Reservation {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String surname;
    private int seats;
    private String telephone;
    private LocalDate date;
    private LocalTime time;
    @Column(name = "special_request")
    private String specialRequest;
    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private EventType eventType;
    @Enumerated(EnumType.STRING)
    @Column(name = "reservation_type")
    private ReservationType reservationType;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Reservation(String name, String surname, int seats, String telephone, LocalDate date, LocalTime time, String specialRequest, EventType eventType, ReservationType reservationType, User user) {
        this.name = name;
        this.surname = surname;
        this.seats = seats;
        this.telephone = telephone;
        this.date = date;
        this.time = time;
        this.specialRequest = specialRequest;
        this.eventType = eventType;
        this.reservationType = reservationType;
        this.user = user;
    }
}
