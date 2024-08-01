package giovannighirardelli.MaulbeereIMP.servicies;


import giovannighirardelli.MaulbeereIMP.entities.Dinner;
import giovannighirardelli.MaulbeereIMP.entities.User;
import giovannighirardelli.MaulbeereIMP.payloads.DinnerDTO.DinnerDTO;
import giovannighirardelli.MaulbeereIMP.repositories.DinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DinnerService {
    @Autowired
    private DinnerRepository dinnerRepository;
    @Autowired
    private UserService userService;

    public Page<Dinner> getAllDinner(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return dinnerRepository.findAll(pageable);
    }

    public Dinner saveDinner(DinnerDTO body) {
        User found= this.userService.findById(body.user());
        Optional<Dinner> existingDinner = dinnerRepository.findByUser(found);
        existingDinner.ifPresent(dinnerRepository::delete);
        Dinner dinner = new Dinner(body.monday(), body.tuesday(), body.wednesday(), body.thursday(), body.friday(), body.saturday(), body.sunday(), found);

        return dinnerRepository.save(dinner);
    }


}
