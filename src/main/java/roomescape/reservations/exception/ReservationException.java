package roomescape.reservations.exception;

public class ReservationException extends RuntimeException {

    private final Object debugData;

    public ReservationException(String clientMessage) {
        this(clientMessage, null);
    }

    public ReservationException(String clientMessage, Object debugData) {
        super(clientMessage);
        this.debugData = debugData;
    }

    public Object getDebugData() {
        return debugData;
    }
}