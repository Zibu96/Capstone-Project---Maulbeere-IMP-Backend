package giovannighirardelli.MaulbeereIMP.entities;

import giovannighirardelli.MaulbeereIMP.enums.ActionType;
import giovannighirardelli.MaulbeereIMP.enums.StaffType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "staff_organizer")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class StaffOrganizer {
    @Id
    @GeneratedValue
    private UUID id;
    @Enumerated(EnumType.STRING)
    private StaffType staffType;
    @Enumerated(EnumType.STRING)
    private ActionType actionType;
    private String text;


    public StaffOrganizer(StaffType staffType, ActionType actionType, String text) {
        this.staffType = staffType;
        this.actionType = actionType;
        this.text = text;
    }
}
