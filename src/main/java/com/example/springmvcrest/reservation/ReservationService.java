package com.example.springmvcrest.reservation;

import com.example.springmvcrest.room.Room;
import com.example.springmvcrest.room.RoomRepository;
import com.example.springmvcrest.user.User;
import com.example.springmvcrest.user.UserNotFoundException;
import com.example.springmvcrest.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private UserRepository userRepository;
    private RoomRepository roomRepository;
    private ReservationMapper reservationMapper;

    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository, RoomRepository roomRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.reservationMapper = reservationMapper;
    }

    void createReservation(String currentUserLogin, Long roomId, ReservationDto reservationDto) {
        Room roomToBook = roomRepository.getById(roomId);
        Optional<User> userBookRoom = userRepository.findByEmail(currentUserLogin);

        if (userBookRoom.isPresent()) {
            reservationDto.setUserId(userBookRoom.get().getId());
            reservationDto.setRoomId(roomId);
            reservationDto.setStart(reservationDto.getStart());
            reservationDto.setEnd(reservationDto.getEnd());
            roomToBook.setAvailable(false);
            roomRepository.save(roomToBook);

            reservationRepository.save(reservationMapper.toEntity(reservationDto));

        } else {
            throw new UserNotFoundException();
        }

    }
}
