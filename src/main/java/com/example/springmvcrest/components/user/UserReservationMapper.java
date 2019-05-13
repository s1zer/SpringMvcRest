package com.example.springmvcrest.components.user;

import com.example.springmvcrest.components.reservation.Reservation;

public class UserReservationMapper {

    static UserReservationDto toDto(Reservation reservation) {
        UserReservationDto userReservationDto = new UserReservationDto();
        userReservationDto.setId(reservation.getId());
        userReservationDto.setStart(reservation.getStart());
        userReservationDto.setEnd(reservation.getEnd());
        userReservationDto.setRoomId(reservation.getRoom().getId());
        userReservationDto.setCharge(reservation.getCharge());
        userReservationDto.setRoomNumber(reservation.getRoom().getNumber());

        return userReservationDto;
    }
}
