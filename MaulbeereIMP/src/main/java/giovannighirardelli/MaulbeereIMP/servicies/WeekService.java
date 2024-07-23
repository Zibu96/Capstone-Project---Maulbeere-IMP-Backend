package giovannighirardelli.MaulbeereIMP.servicies;


import giovannighirardelli.MaulbeereIMP.entities.User;
import giovannighirardelli.MaulbeereIMP.entities.Week;

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

        Week week = new Week(convertStringToWeekDays(body.weekDays()), body.lunchUser(), body.dinnerUserOne(), body.dinnerUserTwo(), body.dinnerUserThree());

        return weekRepository.save(week);
    }


    private static WeekDays convertStringToWeekDays (String day){
        try{
            return WeekDays.valueOf(day.toUpperCase());
        }catch (IllegalArgumentException e) {
            throw new BadRequestException("The selected role don't exists");
        }
    }

    public UUID convertLunchUserToId (Week body) {

        if (body != null && body.getLunchUser() != null) {
            return UUID.fromString(body.getLunchUser());
        }
        return null;

    }
    public UUID convertDinnerUserOneToId (Week body) {

        if (body != null && body.getDinnerUserOne() != null) {
            return UUID.fromString(body.getDinnerUserOne());
        }
        return null;

    }

    public UUID convertDinnerUserTwoToId (Week body) {

        if (body != null && body.getDinnerUserTwo() != null) {
            return UUID.fromString(body.getDinnerUserTwo());
        }
        return null;

    }

    public UUID convertDinnerUserThreeToId (Week body) {

        if (body != null && body.getDinnerUserThree() != null) {
            return UUID.fromString(body.getDinnerUserThree());
        }
        return null;

    }
    public UUID getFirstValidUserId(Week body) {
        UUID userId = convertLunchUserToId(body);
        if (userId != null) {
            return userId;
        }

        userId = convertDinnerUserOneToId(body);
        if (userId != null) {
            return userId;
        }

        userId = convertDinnerUserTwoToId(body);
        if (userId != null) {
            return userId;
        }

        userId = convertDinnerUserThreeToId(body);
        if (userId != null) {
            return userId;
        }

        return null;
    }

}
