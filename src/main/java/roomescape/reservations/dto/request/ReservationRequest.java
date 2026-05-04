package roomescape.reservations.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationRequest(
        String name,
        LocalTime time,
        LocalDate date
) {
}
