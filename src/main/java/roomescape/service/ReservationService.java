package roomescape.service;

import org.springframework.stereotype.Service;
import roomescape.model.Reservation;
import roomescape.repository.ReservationRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final AtomicLong idGenerator = new AtomicLong(1);

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.getAllReservations();
    }

    public Reservation createReservation(Reservation newReservation) {
        Reservation reservation = new Reservation(
                idGenerator.getAndIncrement(),
                newReservation.getName(),
                newReservation.getDate(),
                newReservation.getTime()
        );
        reservationRepository.createReservation(reservation);
        return reservation;
    }

    public void deleteReservationById(Long id) {
        reservationRepository.deleteReservationById(id);
    }
}
