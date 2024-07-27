package giovannighirardelli.MaulbeereIMP.repositories;


import giovannighirardelli.MaulbeereIMP.entities.StaffOrganizer;
import giovannighirardelli.MaulbeereIMP.enums.ActionType;
import giovannighirardelli.MaulbeereIMP.enums.StaffType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface StaffOrganizerRepository extends JpaRepository<StaffOrganizer, UUID> {
    Page<StaffOrganizer> findByActionTypeAndStaffType(ActionType actionType, StaffType staffType, Pageable pageable);
}
