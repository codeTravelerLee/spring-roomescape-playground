package roomescape.reservations.repository;

import org.springframework.stereotype.Repository;
import roomescape.reservations.exception.ReservationException;
import roomescape.reservations.model.Reservation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ReservationRepository {

    private final List<Reservation> reservations = Collections.synchronizedList(new ArrayList<>());

    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations);
    }

    public Reservation getReservationById(Long id) {
        return reservations.stream()
                .filter(reservation -> reservation.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ReservationException("일치하는 예약건이 없어요! 다시 확인해주세요!"));
    }

    public void createReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void deleteReservationById(Long id) {
        Reservation reservationTobeDeleted = reservations.stream()
                .filter(reservation -> reservation.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ReservationException("일치하는 예약건이 없어요! 다시 확인해주세요!"));

        reservations.remove(reservationTobeDeleted);
    }
}
