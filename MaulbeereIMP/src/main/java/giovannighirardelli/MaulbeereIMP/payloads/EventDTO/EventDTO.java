package giovannighirardelli.MaulbeereIMP.payloads.EventDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

public record EventDTO(
        @NotEmpty(message = "The name field is required")
        @Size(min = 2, message = "The name field is min 2")
        String name,
        @NotEmpty(message = "The description field is required")
        String description,
        @NotNull(message = "The date field is required")
        LocalDate date,
        @NotNull(message = "The time field is required")
        LocalTime time,
        @NotEmpty(message = "The name field is required")
        String eventType
){
}
