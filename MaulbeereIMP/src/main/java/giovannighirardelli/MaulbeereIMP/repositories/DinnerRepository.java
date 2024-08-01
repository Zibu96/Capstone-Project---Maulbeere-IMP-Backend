package giovannighirardelli.MaulbeereIMP.repositories;

import giovannighirardelli.MaulbeereIMP.entities.Dinner;
import giovannighirardelli.MaulbeereIMP.entities.Lunch;
import giovannighirardelli.MaulbeereIMP.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DinnerRepository extends JpaRepository<Dinner, UUID> {
    Optional<Dinner> findByUser (User user);
}
