package roomescape.repository;

import roomescape.model.Reservation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReservationRepository {

    private final List<Reservation> reservations = Collections.synchronizedList(new ArrayList<>());

    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations);
    }

    public void createReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public boolean deleteReservationById(Long id) {
        return reservations.removeIf(reservation -> reservation.getId().equals(id));
    }
}
