package com.example.springmvcrest.components.user;

import com.example.springmvcrest.components.message.Message;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "registerForm";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute(name = "user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registerForm";
        } else {
            if (userService.addWithDefaultRole(user)) {
                return "registerSuccess";
            } else {
                model.addAttribute("message", new Message("Error", "This email already exists"));
                return "registerForm";
            }
        }
    }

    @GetMapping("/user")
    public String userPanel() {
        return "userPanel";
    }

    @GetMapping("/user/reservations")
    public String getUserReservations(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        try {
            List<UserReservationDto> userReservations = userService.getUserReservation(currentUser.getUsername());
            model.addAttribute("userReservations", userReservations);
            return "reservations";
        } catch (UserNotFoundException e) {
            model.addAttribute("message", new Message("Error", "User not found"));
            return "error";
        }
    }

}
