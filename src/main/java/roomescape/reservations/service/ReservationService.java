package roomescape.reservations.service;

import org.springframework.stereotype.Service;
import roomescape.reservations.exception.ReservationException;
import roomescape.reservations.dto.request.ReservationRequest;
import roomescape.reservations.dto.response.ReservationResponse;
import roomescape.reservations.model.Reservation;
import roomescape.reservations.repository.ReservationRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ReservationService {

    private static final int MAX_CAPACITY_PER_TIME = 5;

    private final ReservationRepository reservationRepository;
    private final AtomicLong idGenerator = new AtomicLong(1);

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationResponse> getAllReservations() {
        List<Reservation> reservations = reservationRepository.getAllReservations();
        List<ReservationResponse> reservationResponses = reservations.stream()
                .map(this::convertIntoResponseDTO)
                .toList();

        return reservationResponses;
    }

    public ReservationResponse getReservationById(Long id) {
        Reservation reservation = reservationRepository.getReservationById(id);

        return convertIntoResponseDTO(reservation);
    }

    public ReservationResponse createReservation(ReservationRequest newReservation) {
        Reservation reservation = new Reservation(
                idGenerator.getAndIncrement(),
                newReservation.name(),
                newReservation.date(),
                newReservation.time()
        );

        validateDuplicateReservation(reservation);
        validateCapacityPerTime(reservation.getDate(), reservation.getTime());

        reservationRepository.createReservation(reservation);

        return convertIntoResponseDTO(reservation);
    }

    public void deleteReservationById(Long id) {
        reservationRepository.deleteReservationById(id);
    }

    private void validateCapacityPerTime(LocalDate date, LocalTime time) {
        long currentReservationCount = reservationRepository.getAllReservations().stream()
                .filter(reservation -> reservation.getDate().equals(date) && reservation.getTime().equals(time))
                .count();

        if (currentReservationCount >= MAX_CAPACITY_PER_TIME) {
            throw new ReservationException("선택하신 시간대는 예약이 마감되었어요! 다른 시간대를 찾아봐주세요! (정원: " + MAX_CAPACITY_PER_TIME + "명)");
        }
    }

    private void validateDuplicateReservation(Reservation newReservation) {
        boolean existsBySameUserAtSameTime = reservationRepository.getAllReservations().stream()
                .anyMatch(reservation -> reservation.getDate().equals(newReservation.getDate())
                        && reservation.getTime().equals(newReservation.getTime())
                        && reservation.getName().equals(newReservation.getName()));

        if (existsBySameUserAtSameTime) {
            throw new ReservationException("이미 동일한 시간에 동일한 이름으로 예약건이 있어요!");
        }
    }

    private ReservationResponse convertIntoResponseDTO(Reservation reservation) {
        return new ReservationResponse(reservation.getId(), reservation.getName(), reservation.getDate(), reservation.getTime());
    }
}
