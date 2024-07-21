package giovannighirardelli.MaulbeereIMP.repositories;

import giovannighirardelli.MaulbeereIMP.entities.Dinner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DinnerRepository extends JpaRepository<Dinner, UUID> {
}
