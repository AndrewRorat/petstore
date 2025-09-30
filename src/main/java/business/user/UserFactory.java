package business.user;

import java.util.concurrent.ThreadLocalRandom;

public class UserFactory {

    public User basic(String username) {
        long id = ThreadLocalRandom.current().nextLong(1, 90_000_000);
        return User.builder()
                .id(id).username(username)
                .firstName("John").lastName("Doe")
                .email("john.doe@example.com")
                .password("Secret123!")
                .phone("+100000000")
                .userStatus(1)
                .build();
    }
    public User invalid() { return User.builder().username(null).build(); }
}
