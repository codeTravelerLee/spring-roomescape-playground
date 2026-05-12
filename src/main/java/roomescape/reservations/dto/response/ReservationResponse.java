package roomescape.reservations.dto.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationResponse(
        @NotNull
        @PositiveOrZero
        Long id,
        String name,
        LocalDate date,
        LocalTime time
) {
}
