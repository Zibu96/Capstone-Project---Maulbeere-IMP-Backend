package giovannighirardelli.MaulbeereIMP.entities;


import giovannighirardelli.MaulbeereIMP.enums.WeekDays;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "week")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Week {
    @Id
    @GeneratedValue
    private UUID id;
    @Enumerated(EnumType.STRING)
    private WeekDays weekDays;
    @OneToOne
    @JoinColumn(name = "user_lunch")
    private User lunchUser;
    @OneToOne
    @JoinColumn(name = "user_dinner_one")
    private User dinnerUserOne;
    @OneToOne
    @JoinColumn(name = "user_dinner_two")
    private User dinnerUserTwo;
    @OneToOne
    @JoinColumn(name = "user_dinner_three")
    private User dinnerUserThree;


    public Week(WeekDays weekDays, User lunchUser, User dinnerUserOne, User dinnerUserTwo, User dinnerUserThree) {
        this.weekDays = weekDays;
        this.lunchUser = lunchUser;
        this.dinnerUserOne = dinnerUserOne;
        this.dinnerUserTwo = dinnerUserTwo;
        this.dinnerUserThree = dinnerUserThree;
    }
}
