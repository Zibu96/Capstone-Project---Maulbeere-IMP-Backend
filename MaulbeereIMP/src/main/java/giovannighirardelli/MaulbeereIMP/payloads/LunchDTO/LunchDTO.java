package giovannighirardelli.MaulbeereIMP.payloads.LunchDTO;

import java.util.UUID;

public record LunchDTO(
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
