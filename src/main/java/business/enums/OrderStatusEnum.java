package business.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    PLACED("placed"),
    APPROVED("approved"),
    DELIVERED("delivered");

    private final String name;
}
