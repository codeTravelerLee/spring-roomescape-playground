package roomescape.reservations.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationRequest(
        @NotBlank(message = "에약하실 분의 성함을 입력해주세요!")
        String name,

        @NotNull(message = "예약 시간은 필수로 입력해주셔야 해요!")
        LocalTime time,

        @NotNull(message = "예약 날짜는 필수로 입력해주셔야 해요!")
        LocalDate date
) {
}
