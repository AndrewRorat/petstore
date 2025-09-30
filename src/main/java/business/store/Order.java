package business.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long id;
    private Long petId;
    private Integer quantity;
    private String shipDate;   // ISO-8601 string
    private String status;
    private Boolean complete;
}
