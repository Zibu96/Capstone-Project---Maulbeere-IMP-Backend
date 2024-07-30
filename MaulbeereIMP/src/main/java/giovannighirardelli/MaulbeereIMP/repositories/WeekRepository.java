package giovannighirardelli.MaulbeereIMP.repositories;


import giovannighirardelli.MaulbeereIMP.entities.Week;
import giovannighirardelli.MaulbeereIMP.enums.WeekDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WeekRepository extends JpaRepository<Week, UUID> {
    Optional<Week> findByWeekDays (WeekDays weekDays);
}
