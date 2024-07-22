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

import java.util.Optional;
import java.util.UUID;

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
        User lunch = findUserById(body.lunchUser());
        User dinnerOne = findUserById(body.dinnerUserOne());
        User dinnerTwo = findUserById(body.dinnerUserTwo());
        User dinnerThree = findUserById(body.dinnerUserThree());

        Week week = new Week(convertStringToWeekDays(body.weekDays()), lunch, dinnerOne, dinnerTwo, dinnerThree);

        return weekRepository.save(week);
    }

    private User findUserById(UUID userId) {
        return Optional.ofNullable(userId)
                .map(userService::findById)
                .orElse(null);
    }

    private static WeekDays convertStringToWeekDays (String day){
        try{
            return WeekDays.valueOf(day.toUpperCase());
        }catch (IllegalArgumentException e) {
            throw new BadRequestException("The selected role don't exists");
        }
    }


}
