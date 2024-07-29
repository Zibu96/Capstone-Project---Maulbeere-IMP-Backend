package giovannighirardelli.MaulbeereIMP.controllers;

import giovannighirardelli.MaulbeereIMP.entities.ShoppingList;
import giovannighirardelli.MaulbeereIMP.entities.StaffOrganizer;
import giovannighirardelli.MaulbeereIMP.entities.User;
import giovannighirardelli.MaulbeereIMP.exceptions.BadRequestException;
import giovannighirardelli.MaulbeereIMP.payloads.ReservationDTO.ReservationDTO;
import giovannighirardelli.MaulbeereIMP.payloads.ReservationDTO.ReservationResponseDTO;
import giovannighirardelli.MaulbeereIMP.payloads.ShoppingListDTO.ShoppingListDTO;
import giovannighirardelli.MaulbeereIMP.payloads.ShoppingListDTO.ShoppingListResponseDTO;
import giovannighirardelli.MaulbeereIMP.payloads.StaffOrganizerDTO.StaffOrganizerDTO;
import giovannighirardelli.MaulbeereIMP.payloads.StaffOrganizerDTO.StaffOrganizerResponseDTO;
import giovannighirardelli.MaulbeereIMP.servicies.WaitStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/waitstaff")
public class WaitStaffController {
    @Autowired
    private WaitStaffService waitStaffService;


    @GetMapping("/todos")
    public Page<StaffOrganizer> findAllToDo(@RequestParam(defaultValue = "0") int pageNum,
                                        @RequestParam(defaultValue = "10") int pageSize,
                                        @RequestParam(defaultValue = "id") String sortBy) {

        return this.waitStaffService.getAllToDo(pageNum, pageSize, sortBy);
    }

    @GetMapping("/communications")
    public Page<StaffOrganizer> findAllCommunication(@RequestParam(defaultValue = "0") int pageNum,
                              @RequestParam(defaultValue = "10") int pageSize,
                              @RequestParam(defaultValue = "id") String sortBy) {

        return this.waitStaffService.getAllCommunication(pageNum, pageSize, sortBy);
    }

    @GetMapping("/shoppingLists")
    public Page<ShoppingList> findAllShoppingList(@RequestParam(defaultValue = "0") int pageNum,
                                                   @RequestParam(defaultValue = "10") int pageSize,
                                                   @RequestParam(defaultValue = "id") String sortBy) {

        return this.waitStaffService.getAllShoppingList(pageNum, pageSize, sortBy);
    }

    @PostMapping("/todos")
    public StaffOrganizerResponseDTO saveToDo(@RequestBody @Validated StaffOrganizerDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        System.out.println(body);
        return new StaffOrganizerResponseDTO(this.waitStaffService.saveToDO(body).getId());

    }

    @PostMapping("/communications")
    public StaffOrganizerResponseDTO saveCommunication(@RequestBody @Validated StaffOrganizerDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        System.out.println(body);
        return new StaffOrganizerResponseDTO(this.waitStaffService.saveCommunication(body).getId());

    }
    @PostMapping("/shoppingLists")
    public ShoppingListResponseDTO saveShoppingList(@RequestBody @Validated ShoppingListDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        System.out.println(body);
        return new ShoppingListResponseDTO(this.waitStaffService.saveShoppingList(body).getId());

    }

    @DeleteMapping("/{staffOrganizerId}")
    public void deleteStaffOrganizer(@PathVariable UUID staffOrganizerId) {
        waitStaffService.findByIdAndDelete(staffOrganizerId);
    }

    @DeleteMapping("/{shoppingListId}")
    public void deleteShoppingList(@PathVariable UUID shoppingListId) {
        waitStaffService.findShoppingListByIdAndDelete(shoppingListId);
    }


}
