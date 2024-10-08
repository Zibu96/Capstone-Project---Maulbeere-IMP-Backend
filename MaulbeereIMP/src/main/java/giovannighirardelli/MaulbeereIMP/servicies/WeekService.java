package giovannighirardelli.MaulbeereIMP.servicies;



import giovannighirardelli.MaulbeereIMP.entities.Week;

import giovannighirardelli.MaulbeereIMP.enums.WeekDays;
import giovannighirardelli.MaulbeereIMP.exceptions.BadRequestException;
import giovannighirardelli.MaulbeereIMP.exceptions.NotFoundException;
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



    public Page<Week> getAllWeek(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return weekRepository.findAll(pageable);
    }

    public Week saveWeek(WeekDTO body) {
        Optional<Week> existingWeek = weekRepository.findByWeekDays(convertStringToWeekDays(body.weekDays()));
        existingWeek.ifPresent(week -> weekRepository.delete(week));
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



    public Week findById(UUID id) {
        return this.weekRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }






}
