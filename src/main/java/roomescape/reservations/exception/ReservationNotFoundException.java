package roomescape.reservations.exception;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException() {
        super("일치하는 예약건이 없어요!");
    }

    public ReservationNotFoundException(String customMessage) {
        super(customMessage);
    }
}
