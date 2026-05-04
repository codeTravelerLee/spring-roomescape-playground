package roomescape.global.error;

public class InvalidReservationTimeException extends RuntimeException {

    public InvalidReservationTimeException(String message) {
        super(message);
    }
}
