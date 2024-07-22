package giovannighirardelli.MaulbeereIMP.servicies;


import giovannighirardelli.MaulbeereIMP.entities.Week;
import giovannighirardelli.MaulbeereIMP.entities.User;
import giovannighirardelli.MaulbeereIMP.enums.WeekDays;
import giovannighirardelli.MaulbeereIMP.exceptions.BadRequestException;
import giovannighirardelli.MaulbeereIMP.payloads.WeekDTO.WeekDTO;
import giovannighirardelli.MaulbeereIMP.repositories.WeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class WeekService {
    @Autowired
    private WeekRepository weekRepository;
    @Autowired
    private UserService userService;


    public Page<Week> getAllWeek(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return weekRepository.findAll(pageable);
    }

    public Week saveWeek(WeekDTO body) {
        User lunch = body.lunchUser() != null ? this.userService.findById(body.lunchUser()) : null;
        User dinnerOne = body.dinnerUserOne() != null ? this.userService.findById(body.dinnerUserOne()) : null;
        User dinnerTwo = body.dinnerUserTwo() != null ? this.userService.findById(body.dinnerUserTwo()) : null;
        User dinnerThree = body.dinnerUserThree() != null ? this.userService.findById(body.dinnerUserThree()) : null;


        Week week = new Week(convertStringToWeekDays(body.weekDays()), lunch, dinnerOne, dinnerTwo, dinnerThree);

        return weekRepository.save(week);
    }

    private static WeekDays convertStringToWeekDays (String day){
        try{
            return WeekDays.valueOf(day.toUpperCase());
        }catch (IllegalArgumentException e) {
            throw new BadRequestException("The selected role don't exists");
        }
    }
}
