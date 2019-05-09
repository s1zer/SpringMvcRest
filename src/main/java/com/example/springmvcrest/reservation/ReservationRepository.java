package com.example.springmvcrest.reservation;

import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository {

    static ReservationDto toDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservation.getId());
        reservationDto.setCharge(reservation.getCharge());
        reservationDto.setStart(reservation.getStart());
        reservationDto.setEnd(reservation.getEnd());
        reservationDto.setUserId(reservation.getUser().getId());
        reservationDto.setRoomId(reservation.getRoom().getId());
        return reservationDto;
    }
}
