package com.example.springmvcrest.reservation;

import com.example.springmvcrest.user.UserNotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reservation/create")
    public String createReservation(@AuthenticationPrincipal UserDetails currentUser, @RequestParam Long roomId, ReservationDto reservation) {
        try {
            reservationService.createReservation(currentUser.getUsername(), roomId, reservation);
            return "redirect:/user/reservations";
        } catch (UserNotFoundException e) {
            return "error";
        }
    }
}
