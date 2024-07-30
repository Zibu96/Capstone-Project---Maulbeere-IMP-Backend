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
    @Column(name = "lunch_user")
    private String lunchUser;
    @Column(name = "dinner_user_one")
    private String dinnerUserOne;
    @Column(name = "dinner_user_two")
    private String dinnerUserTwo;
    @Column(name = "dinner_user_three")
    private String dinnerUserThree;


    public Week(WeekDays weekDays, String lunchUser, String dinnerUserOne, String dinnerUserTwo, String dinnerUserThree) {
        this.weekDays = weekDays;
        this.lunchUser = lunchUser;
        this.dinnerUserOne = dinnerUserOne;
        this.dinnerUserTwo = dinnerUserTwo;
        this.dinnerUserThree = dinnerUserThree;
    }
}
