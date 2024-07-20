package giovannighirardelli.MaulbeereIMP.controllers;


import giovannighirardelli.MaulbeereIMP.entities.User;
import giovannighirardelli.MaulbeereIMP.payloads.UsersDTO.UserDTO;
import giovannighirardelli.MaulbeereIMP.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN', 'GESTORE')")
    public Page<User> findAll(@RequestParam(defaultValue = "0") int pageNum,
                              @RequestParam(defaultValue = "10") int pageSize,
                              @RequestParam(defaultValue = "id") String sortBy) {

        return this.userService.getAllUsers(pageNum, pageSize, sortBy);
    }


    @GetMapping("/me")
    public User getOwnProfile(@AuthenticationPrincipal User currentAuthenticatedUser) {
        return userService.findById(currentAuthenticatedUser.getId());
    }


    @DeleteMapping("/me")
    public void deleteOwnProfile(@AuthenticationPrincipal User currentAuthenticatedUser) {
        userService.findUserByIdAndDelete(currentAuthenticatedUser.getId());
    }

    @PatchMapping("/me/passwords")
    public User changePassword(@AuthenticationPrincipal User currentAuthenticatedUser,  @RequestBody UserDTO body) {
        return userService.changePassword(currentAuthenticatedUser.getId(), body);
    }

    @PatchMapping("/me/emails")
    public User changeEmail(@AuthenticationPrincipal User currentAuthenticatedUser,  @RequestBody UserDTO body) {
        return userService.changeEmail(currentAuthenticatedUser.getId(), body);
    }
}
