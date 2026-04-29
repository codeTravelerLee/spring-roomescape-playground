package roomescape.reservations.service;

import org.springframework.stereotype.Service;
import roomescape.reservations.model.Reservation;
import roomescape.reservations.repository.ReservationRepository;

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

    public Reservation getReservationById(Long id) {
        return reservationRepository.getReservationById(id);
    }

    public Reservation createReservation(Reservation newReservation) {
        boolean existsBySameUserAtSameTime = reservationRepository.getAllReservations().stream()
                .anyMatch(reservation -> reservation.getDate().equals(newReservation.getDate())
                        && reservation.getTime().equals(newReservation.getTime())
                        && reservation.getName().equals(newReservation.getName()));

        if (existsBySameUserAtSameTime) {
            throw new IllegalArgumentException("이미 동일한 시간에 동일한 이름으로 예약건이 있어요!");
        }

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
