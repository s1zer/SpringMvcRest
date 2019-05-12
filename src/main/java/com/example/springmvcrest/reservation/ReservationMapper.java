package com.example.springmvcrest.reservation;

import com.example.springmvcrest.room.Room;
import com.example.springmvcrest.room.RoomRepository;
import com.example.springmvcrest.user.User;
import com.example.springmvcrest.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationMapper {

    private RoomRepository roomRepository;
    private UserRepository userRepository;

    public ReservationMapper(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

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

    Reservation toEntity(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationDto.getId());
        reservation.setStart(reservationDto.getStart());
        reservation.setEnd(reservationDto.getEnd());
        reservation.setCharge(reservationDto.getCharge());

        Optional<Room> findRoom = roomRepository.findById(reservationDto.getRoomId());
        findRoom.ifPresent(r -> reservation.setRoom(r));

        Optional<User> user = userRepository.findById(reservationDto.getUserId());
        user.ifPresent(u -> reservation.setUser(u));

        return reservation;
    }
}
