package giovannighirardelli.MaulbeereIMP.repositories;

import giovannighirardelli.MaulbeereIMP.entities.Lunch;
import giovannighirardelli.MaulbeereIMP.entities.User;
import giovannighirardelli.MaulbeereIMP.entities.Week;
import giovannighirardelli.MaulbeereIMP.enums.WeekDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LunchRepository extends JpaRepository<Lunch, UUID> {
    Optional<Lunch> findByUser (User user);
}
