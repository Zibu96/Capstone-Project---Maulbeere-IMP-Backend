package giovannighirardelli.MaulbeereIMP.controllers;


import giovannighirardelli.MaulbeereIMP.entities.ShoppingList;
import giovannighirardelli.MaulbeereIMP.entities.StaffOrganizer;
import giovannighirardelli.MaulbeereIMP.exceptions.BadRequestException;
import giovannighirardelli.MaulbeereIMP.payloads.ShoppingListDTO.ShoppingListDTO;
import giovannighirardelli.MaulbeereIMP.payloads.ShoppingListDTO.ShoppingListResponseDTO;
import giovannighirardelli.MaulbeereIMP.payloads.StaffOrganizerDTO.StaffOrganizerDTO;
import giovannighirardelli.MaulbeereIMP.payloads.StaffOrganizerDTO.StaffOrganizerResponseDTO;
import giovannighirardelli.MaulbeereIMP.servicies.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/kitchen")
public class KitchenController {

    @Autowired
    private KitchenService kitchenService;

    @GetMapping("/todos")
    public Page<StaffOrganizer> findAllToDo(@RequestParam(defaultValue = "0") int pageNum,
                                            @RequestParam(defaultValue = "10") int pageSize,
                                            @RequestParam(defaultValue = "id") String sortBy) {

        return this.kitchenService.getAllToDo(pageNum, pageSize, sortBy);
    }

    @GetMapping("/communications")
    public Page<StaffOrganizer> findAllCommunication(@RequestParam(defaultValue = "0") int pageNum,
                                                     @RequestParam(defaultValue = "10") int pageSize,
                                                     @RequestParam(defaultValue = "id") String sortBy) {

        return this.kitchenService.getAllCommunication(pageNum, pageSize, sortBy);
    }

    @GetMapping("/shoppingLists")
    public Page<ShoppingList> findAllShoppingList(@RequestParam(defaultValue = "0") int pageNum,
                                                  @RequestParam(defaultValue = "10") int pageSize,
                                                  @RequestParam(defaultValue = "id") String sortBy) {

        return this.kitchenService.getAllShoppingList(pageNum, pageSize, sortBy);
    }

    @PostMapping("/todos")
    public StaffOrganizerResponseDTO saveToDo(@RequestBody @Validated StaffOrganizerDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        System.out.println(body);
        return new StaffOrganizerResponseDTO(this.kitchenService.saveToDO(body).getId());

    }

    @PostMapping("/communications")
    public StaffOrganizerResponseDTO saveReservation(@RequestBody @Validated StaffOrganizerDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        System.out.println(body);
        return new StaffOrganizerResponseDTO(this.kitchenService.saveCommunication(body).getId());

    }

    @PostMapping("/shoppingLists")
    public ShoppingListResponseDTO saveShoppingList(@RequestBody @Validated ShoppingListDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        System.out.println(body);
        return new ShoppingListResponseDTO(this.kitchenService.saveShoppingList(body).getId());

    }

    @DeleteMapping("/{staffOrganizerId}")
    public void deleteStaffOrganizer(@PathVariable UUID staffOrganizerId) {
        kitchenService.findByIdAndDelete(staffOrganizerId);
    }


    @DeleteMapping("/{shoppingListId}")
    public void deleteShoppingList(@PathVariable UUID shoppingListId) {
        kitchenService.findShoppingListByIdAndDelete(shoppingListId);
    }
}
