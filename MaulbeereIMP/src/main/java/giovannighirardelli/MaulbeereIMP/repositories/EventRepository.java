package giovannighirardelli.MaulbeereIMP.repositories;


import giovannighirardelli.MaulbeereIMP.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    List<Event> getByDate (LocalDate date);

    void deleteByDateBefore(LocalDate date);
}
