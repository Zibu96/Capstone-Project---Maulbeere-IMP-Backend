package giovannighirardelli.MaulbeereIMP.payloads.ReservationDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record ReservationDTO(
        @NotEmpty(message = "The name field is required")
        @Size(min = 2, message = "The name field is min 2")
        String name,
        String surname,
        @NotNull(message = "The seats field is required")
        @Size(min = 1, message = "Need min 1 client for the reservation")
        int seats,
        @NotEmpty(message = "The telephone field is required")
        String telephone,
        @NotNull(message = "The date field is required")
        LocalDate date,
        @NotNull(message = "The time field is required")
        LocalTime time,
        String specialRequest,
        String eventType,
        String reservationType,
        UUID user


        ) {

}
