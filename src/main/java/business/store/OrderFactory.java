package business.store;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
public class OrderFactory {
    public Order valid() {
        long id = ThreadLocalRandom.current().nextLong(1, 10);
        return Order.builder()
                .id(id)
                .petId(1L)
                .quantity(2)
                .shipDate(LocalDateTime.now().toString())
                .status("placed")
                .complete(true)
                .build();
    }

    public Order invalidQuantity() {
        Order o = valid();
        o.setQuantity(-1);
        return o;
    }
}
