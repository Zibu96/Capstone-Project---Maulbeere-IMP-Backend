package giovannighirardelli.MaulbeereIMP.controllers;


import giovannighirardelli.MaulbeereIMP.entities.Dinner;
import giovannighirardelli.MaulbeereIMP.exceptions.BadRequestException;
import giovannighirardelli.MaulbeereIMP.payloads.DinnerDTO.DinnerDTO;
import giovannighirardelli.MaulbeereIMP.payloads.DinnerDTO.DinnerResponseDTO;
import giovannighirardelli.MaulbeereIMP.servicies.DinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dinners")
public class DinnerController {
    @Autowired
    private DinnerService dinnerService;


    @GetMapping
    public Page<Dinner> getDinner(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {

        return this.dinnerService.getAllDinner(page, size, sortBy);
    }


    @PostMapping
    public DinnerResponseDTO saveDinner(@RequestBody @Validated DinnerDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        System.out.println(body);
        return new DinnerResponseDTO(this.dinnerService.saveDinner(body).getId());

    }
}
