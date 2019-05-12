package com.example.springmvcrest.reservation;

import com.example.springmvcrest.room.Room;
import com.example.springmvcrest.room.RoomRepository;
import com.example.springmvcrest.user.User;
import com.example.springmvcrest.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
        Optional<Room> findRoom = roomRepository.findById(roomId);
        Optional<User> findUser = userRepository.findByEmail(currentUserLogin);

        if (findRoom.isPresent() && findUser.isPresent()) {
            Room roomToBook = findRoom.get();
            User user = findUser.get();
            reservationDto.setUserId(user.getId());
            reservationDto.setRoomId(roomId);
            reservationDto.setStart(reservationDto.getStart());
            reservationDto.setEnd(reservationDto.getEnd());
            reservationDto.setCharge(calculateCharge(reservationDto.getStart(), reservationDto.getEnd(), roomToBook.getPrice()));
            roomToBook.setAvailable(false);
            roomRepository.save(roomToBook);
            reservationRepository.save(reservationMapper.toEntity(reservationDto));
        } else {
            throw new InvalidReservationException();
        }
    }

    private double calculateCharge(LocalDate start, LocalDate end, double price) {
        if (start.isBefore(end)) {
            int daysToStay = (int) ChronoUnit.DAYS.between(start, end);
            double charge = price * daysToStay;
            return charge;
        } else {
            throw new InvalidReservationException();
        }
    }


}
