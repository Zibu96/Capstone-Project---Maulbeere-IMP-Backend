package giovannighirardelli.MaulbeereIMP.servicies;


import giovannighirardelli.MaulbeereIMP.entities.Lunch;
import giovannighirardelli.MaulbeereIMP.entities.User;
import giovannighirardelli.MaulbeereIMP.exceptions.BadRequestException;
import giovannighirardelli.MaulbeereIMP.payloads.LunchDTO.LunchDTO;
import giovannighirardelli.MaulbeereIMP.payloads.UsersDTO.UserDTO;
import giovannighirardelli.MaulbeereIMP.repositories.LunchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class LunchService {
    @Autowired
    private LunchRepository lunchRepository;
    @Autowired UserService userService;


    public Page<Lunch> getAllLunch(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return lunchRepository.findAll(pageable);
    }

    public Lunch saveLunch(LunchDTO body) {
        User found= this.userService.findById(body.user());

        Lunch lunch = new Lunch(body.monday(), body.tuesday(), body.wednesday(), body.thursday(), body.friday(), body.saturday(), body.sunday(), found);

        return lunchRepository.save(lunch);
    }



}
