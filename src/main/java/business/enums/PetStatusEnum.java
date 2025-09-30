package business.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PetStatusEnum {

    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold");

    private final String name;
}
