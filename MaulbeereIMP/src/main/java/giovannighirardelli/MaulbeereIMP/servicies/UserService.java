package giovannighirardelli.MaulbeereIMP.servicies;


import giovannighirardelli.MaulbeereIMP.entities.User;
import giovannighirardelli.MaulbeereIMP.enums.Role;
import giovannighirardelli.MaulbeereIMP.exceptions.BadRequestException;
import giovannighirardelli.MaulbeereIMP.exceptions.NotFoundException;
import giovannighirardelli.MaulbeereIMP.payloads.UsersDTO.UserDTO;
import giovannighirardelli.MaulbeereIMP.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bCrypt;


    public Page<User> getAllUsers(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return userRepository.findAll(pageable);
    }

    public User saveUser(UserDTO body) {
        this.userRepository.findByEmail(body.email()).ifPresent(utente -> {
            throw new BadRequestException("The user with email: " + body.email() + ", already exist.");
        });

        User user = new User(body.name(), body.surname(), body.username(), body.email(), bCrypt.encode(body.password()), convertStringToRuoliUtente(body.role()));

        return userRepository.save(user);
    }



    public User findById(UUID id) {
        return this.userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    public void findByIdAndDelete(UUID id) {
        User found = this.findById(id);
        this.userRepository.delete(found);
    }



    private static Role convertStringToRuoliUtente (String ruoli){
        try{
            return Role.valueOf(ruoli.toUpperCase());
        }catch (IllegalArgumentException e) {
            throw new BadRequestException("The selected role don't exists");
        }
        }


    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("The user with email: " + email + ", already exist."));
    }

    }