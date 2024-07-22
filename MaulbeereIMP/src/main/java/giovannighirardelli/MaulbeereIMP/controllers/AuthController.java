package giovannighirardelli.MaulbeereIMP.controllers;

import giovannighirardelli.MaulbeereIMP.exceptions.BadRequestException;
import giovannighirardelli.MaulbeereIMP.payloads.UsersDTO.UserDTO;
import giovannighirardelli.MaulbeereIMP.payloads.UsersDTO.UserLoginDTO;
import giovannighirardelli.MaulbeereIMP.payloads.UsersDTO.UserLoginResponseDTO;
import giovannighirardelli.MaulbeereIMP.payloads.UsersDTO.UserResponseDTO;
import giovannighirardelli.MaulbeereIMP.servicies.AuthService;
import giovannighirardelli.MaulbeereIMP.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO payload) {
        return new UserLoginResponseDTO(authService.authenticateUtenteAndGenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasAuthority('ADMIN')")
    public UserResponseDTO saveUtenti(@RequestBody @Validated UserDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        System.out.println(body);
        return new UserResponseDTO(this.userService.saveUser(body).getId());
    }
}