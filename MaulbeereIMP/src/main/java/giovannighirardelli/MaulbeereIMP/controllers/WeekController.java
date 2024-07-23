package giovannighirardelli.MaulbeereIMP.controllers;

import giovannighirardelli.MaulbeereIMP.entities.User;
import giovannighirardelli.MaulbeereIMP.entities.Week;
import giovannighirardelli.MaulbeereIMP.exceptions.BadRequestException;
import giovannighirardelli.MaulbeereIMP.payloads.DinnerDTO.DinnerResponseDTO;
import giovannighirardelli.MaulbeereIMP.payloads.WeekDTO.WeekDTO;
import giovannighirardelli.MaulbeereIMP.payloads.WeekDTO.WeekResponseDTO;
import giovannighirardelli.MaulbeereIMP.servicies.WeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weeks")
public class WeekController {
    @Autowired
    private WeekService weekService;

    @GetMapping
    public Page<Week> getWeek(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                              @RequestParam(defaultValue = "id") String sortBy) {

        return this.weekService.getAllWeek(page, size, sortBy);
    }


    @PostMapping
    public WeekResponseDTO saveWeek(@RequestBody @Validated WeekDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        System.out.println(body);
        return new WeekResponseDTO(this.weekService.saveWeek(body).getId());

    }


}
