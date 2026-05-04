package roomescape.global.error;

public class ReservationFullException extends RuntimeException {

    public ReservationFullException(String message) {
        super(message);
    }
}
