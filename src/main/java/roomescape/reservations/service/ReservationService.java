package roomescape.reservations.service;

import org.springframework.stereotype.Service;
import roomescape.reservations.model.Reservation;
import roomescape.reservations.repository.ReservationRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ReservationService {
    private static final LocalTime OPENING_TIME = LocalTime.of(10, 0);
    private static final LocalTime CLOSING_TIME = LocalTime.of(22, 0);
    private static final int MAX_CAPACITY_PER_TIME = 5;

    private final ReservationRepository reservationRepository;
    private final AtomicLong idGenerator = new AtomicLong(1);

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.getAllReservations();
    }

    public Reservation getReservationById(Long id) {
        Reservation reservation = reservationRepository.getReservationById(id);
        validateReservationExists(reservation);

        return reservation;
    }

    public Reservation createReservation(Reservation newReservation) {

        validateReservationTimeIsNotPast(newReservation.getDate(), newReservation.getTime());
        validateDuplicateReservation(newReservation);
        validateReservationInBusinessHour(newReservation.getTime());
        validateCapacityPerTime(newReservation.getDate(), newReservation.getTime());

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

    private void validateReservationInBusinessHour(LocalTime reservationTime) {
        if (reservationTime.isBefore(OPENING_TIME) || reservationTime.isAfter(CLOSING_TIME)) {
            throw new IllegalArgumentException("영업 시간외에는 예약이 불가능해요!");
        }
    }

    private void validateCapacityPerTime(LocalDate date, LocalTime time) {
        long currentReservationCount = reservationRepository.getAllReservations().stream()
                .filter(reservation -> reservation.getDate().equals(date) && reservation.getTime().equals(time))
                .count();

        if (currentReservationCount >= MAX_CAPACITY_PER_TIME) {
            throw new IllegalArgumentException("선택하신 시간대는 예약이 마감되었어요! 다른 시간대를 찾아봐주세요! (정원: " + MAX_CAPACITY_PER_TIME + "명)");
        }
    }

    private void validateReservationTimeIsNotPast(LocalDate date, LocalTime time) {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        if (date.isBefore(today)) {
            throw new IllegalArgumentException("지난 날짜로는 예약할 수 없어요!");
        }

        if (date.isEqual(today) && time.isBefore(now)) {
            throw new IllegalArgumentException("이미 지난 시간으로는 예약이 불가능해요!");
        }
    }

    private void validateDuplicateReservation(Reservation newReservation) {
        boolean existsBySameUserAtSameTime = reservationRepository.getAllReservations().stream()
                .anyMatch(reservation -> reservation.getDate().equals(newReservation.getDate())
                        && reservation.getTime().equals(newReservation.getTime())
                        && reservation.getName().equals(newReservation.getName()));

        if (existsBySameUserAtSameTime) {
            throw new IllegalArgumentException("이미 동일한 시간에 동일한 이름으로 예약건이 있어요!");
        }
    }

    private void validateReservationExists(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("해당 id로는 예약건이 존재하지 않아요! 다시 확인해주세요.");
        }
    }
}
