package giovannighirardelli.MaulbeereIMP.repositories;

import giovannighirardelli.MaulbeereIMP.entities.Lunch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LunchRepository extends JpaRepository<Lunch, UUID> {
}
