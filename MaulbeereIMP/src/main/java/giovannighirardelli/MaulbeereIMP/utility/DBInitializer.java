package giovannighirardelli.MaulbeereIMP.utility;

import giovannighirardelli.MaulbeereIMP.entities.User;
import giovannighirardelli.MaulbeereIMP.enums.Role;
import giovannighirardelli.MaulbeereIMP.exceptions.BadRequestException;
import giovannighirardelli.MaulbeereIMP.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//@Component
public class DBInitializer {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bCrypt;


    @Bean
    public void initializeUsers(){
        User newUser = new User("Giovanni", "Ghirardelli", "GhiGio", "gianni_96@hotmail.it", bCrypt.encode("12345678"), Role.ADMIN);
        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) return;
        else userRepository.save(newUser);

    }


}
