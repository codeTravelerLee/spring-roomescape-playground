package roomescape.reservations.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationRequest(
        @NotBlank
        String name,
        LocalTime time,
        LocalDate date
) {
}
