package giovannighirardelli.MaulbeereIMP.servicies;


import giovannighirardelli.MaulbeereIMP.entities.StaffOrganizer;
import giovannighirardelli.MaulbeereIMP.enums.ActionType;
import giovannighirardelli.MaulbeereIMP.enums.StaffType;
import giovannighirardelli.MaulbeereIMP.exceptions.BadRequestException;
import giovannighirardelli.MaulbeereIMP.exceptions.NotFoundException;
import giovannighirardelli.MaulbeereIMP.payloads.StaffOrganizerDTO.StaffOrganizerDTO;
import giovannighirardelli.MaulbeereIMP.repositories.StaffOrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KitchenService {
    @Autowired
    private StaffOrganizerRepository staffOrganizerRepository;

    public Page<StaffOrganizer> getAllToDo(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return staffOrganizerRepository.findByActionType(convertStringToActionType("TO_DO"), pageable);
    }

    public Page<StaffOrganizer> getAllCommunication(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return staffOrganizerRepository.findByActionType(convertStringToActionType("COMUNICAZIONE"), pageable);
    }

    public StaffOrganizer saveToDO(StaffOrganizerDTO body) {

        StaffOrganizer organizer = new StaffOrganizer(convertStringToStaffType("SALA"), convertStringToActionType("TO_DO"), body.text());

        return staffOrganizerRepository.save(organizer);
    }

    public StaffOrganizer saveCommunication(StaffOrganizerDTO body) {

        StaffOrganizer organizer = new StaffOrganizer(convertStringToStaffType("SALA"), convertStringToActionType("COMUNICAZIONE"), body.text());

        return staffOrganizerRepository.save(organizer);
    }


    private static StaffType convertStringToStaffType (String type){
        try{
            return StaffType.valueOf(type.toUpperCase());
        }catch (IllegalArgumentException e) {
            throw new BadRequestException("The selected staff type don't exists");
        }
    }

    private static ActionType convertStringToActionType (String type){
        try{
            return ActionType.valueOf(type.toUpperCase());
        }catch (IllegalArgumentException e) {
            throw new BadRequestException("The selected staff type don't exists");
        }
    }

    public StaffOrganizer findById(UUID id) {
        return this.staffOrganizerRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(UUID id) {
        StaffOrganizer found = this.findById(id);
        this.staffOrganizerRepository.delete(found);
    }
}
