package giovannighirardelli.MaulbeereIMP.controllers;

import giovannighirardelli.MaulbeereIMP.entities.Lunch;

import giovannighirardelli.MaulbeereIMP.exceptions.BadRequestException;
import giovannighirardelli.MaulbeereIMP.payloads.LunchDTO.LunchDTO;
import giovannighirardelli.MaulbeereIMP.payloads.LunchDTO.LunchResponseDTO;
import giovannighirardelli.MaulbeereIMP.servicies.LunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lunches")
public class LunchController {
    @Autowired
    private LunchService lunchService;


    @GetMapping
    public Page<Lunch> getLunch(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id") String sortBy) {

        return this.lunchService.getAllLunch(page, size, sortBy);
    }


    @PostMapping
    public LunchResponseDTO saveLunch(@RequestBody @Validated LunchDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        System.out.println(body);
        return new LunchResponseDTO(this.lunchService.saveLunch(body).getId());

    }
}
