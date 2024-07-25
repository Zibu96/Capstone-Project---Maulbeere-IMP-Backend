package giovannighirardelli.MaulbeereIMP.payloads.StaffOrganizerDTO;

import jakarta.validation.constraints.NotEmpty;

public record StaffOrganizerDTO(
        @NotEmpty(message = "The type of staff is required")
        String staffType,
        @NotEmpty(message = "The type of action is required")
        String actionType,
        @NotEmpty(message = "The message is required")
        String text

) {
}
