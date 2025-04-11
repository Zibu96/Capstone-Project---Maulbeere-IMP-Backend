package giovannighirardelli.MaulbeereIMP.utility;

import giovannighirardelli.MaulbeereIMP.entities.User;
import giovannighirardelli.MaulbeereIMP.enums.Role;
import giovannighirardelli.MaulbeereIMP.repositories.EventRepository;
import giovannighirardelli.MaulbeereIMP.repositories.ReservationRepository;
import giovannighirardelli.MaulbeereIMP.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
public class DBInitializer {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bCrypt;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private EventRepository eventRepository;

    @Value("${ADMIN_PASSWORD}")
    private String adminPassword;

    @Bean
    public void initializeUsers(){
        User newUser = new User("Giovanni", "Ghirardelli", "GhiGio", "gianni_96@hotmail.it", bCrypt.encode(adminPassword), Role.ADMIN);
        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) return;
        else userRepository.save(newUser);

    }

    @Bean
    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void cleanReservationDb(){
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        reservationRepository.deleteByDateBefore(oneMonthAgo);
    }

    @Bean
    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void cleanEventDb(){
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        eventRepository.deleteByDateBefore(oneMonthAgo);
    }
}
