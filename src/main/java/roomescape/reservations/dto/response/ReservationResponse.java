package roomescape.reservations.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationResponse(
        Long Id,
        String name,
        LocalDate date,
        LocalTime time
) {
}
