package giovannighirardelli.MaulbeereIMP.entities;

import giovannighirardelli.MaulbeereIMP.enums.StaffType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "shopping_list")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ShoppingList {
    @Id
    @GeneratedValue
    private UUID id;
    @Enumerated(EnumType.STRING)
    private StaffType staffType;
    private String product;
    private String quantity;
    private String value;
    private LocalDate date;

    public ShoppingList(StaffType staffType, String product, String quantity, String value) {
        this.staffType = staffType;
        this.product = product;
        this.quantity = quantity;
        this.value = value;
        this.date = LocalDate.now();
    }
}
