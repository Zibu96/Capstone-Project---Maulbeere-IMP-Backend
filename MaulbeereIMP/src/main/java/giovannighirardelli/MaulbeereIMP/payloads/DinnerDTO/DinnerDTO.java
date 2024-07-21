package giovannighirardelli.MaulbeereIMP.payloads.DinnerDTO;

import java.util.UUID;

public record DinnerDTO(
        boolean monday,
        boolean tuesday,
        boolean wednesday,
        boolean thursday,
        boolean friday,
        boolean saturday,
        boolean sunday,
        UUID user
) {
}
