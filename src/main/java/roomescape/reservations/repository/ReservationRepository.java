package roomescape.reservations.repository;

import org.springframework.stereotype.Repository;
import roomescape.reservations.exception.ReservationNotFoundException;
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
                .orElseThrow(ReservationNotFoundException::new);
    }

    public void createReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void deleteReservationById(Long id) {
        Reservation reservationTobeDeleted = reservations.stream()
                .filter(reservation -> reservation.getId().equals(id))
                .findFirst()
                .orElseThrow(ReservationNotFoundException::new);

        reservations.remove(reservationTobeDeleted);
    }
}
