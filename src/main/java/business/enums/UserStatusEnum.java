package business.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatusEnum {

    ACTIVE("active", 1),
    INACTIVE("inactive", 0),
    BANNED("banned", 2);

    private final String name;
    private final int code;
}
