package giovannighirardelli.MaulbeereIMP.payloads.ShoppingListDTO;

import jakarta.validation.constraints.NotEmpty;

public record ShoppingListDTO(
        @NotEmpty(message = "The product is required")
        String product,
        @NotEmpty(message = "The quantity is required")
        String quantity,
        String value,
        String staffType
) {
}
