package giovannighirardelli.MaulbeereIMP.payloads.WeekDTO;

import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record WeekDTO(
        @NotEmpty(message = "Day of week field is required")
        String weekDays,
        UUID lunchUser,
        UUID dinnerUserOne,
        UUID dinnerUserTwo,
        UUID dinnerUserThree

) {
}
