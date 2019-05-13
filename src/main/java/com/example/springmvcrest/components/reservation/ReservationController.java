package com.example.springmvcrest.components.reservation;

import com.example.springmvcrest.components.message.Message;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reservation/create")
    public String createReservation(@AuthenticationPrincipal UserDetails currentUser, @RequestParam Long roomId, ReservationDto reservation, Model model) {
        try {
            reservationService.createReservation(currentUser.getUsername(), roomId, reservation);
            return "redirect:/user/reservations";
        } catch (InvalidReservationException e) {
            model.addAttribute("message", new Message("Error", "Invalid data of your reservation. Try again."));
            return "error";
        }
    }
}
