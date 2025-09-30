package business.pet;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PetFactory {

    public Pet availableDog(String name) {
        long id = ThreadLocalRandom.current().nextLong(1, 90_000_000);
        return Pet.builder()
                .id(id)
                .category(Category.builder().id(10L).name("dogs").build())
                .name(name)
                .photoUrls(List.of("http://example.com/p1.jpg"))
                .tags(List.of(Tag.builder().id(100L).name("friendly").build()))
                .status("available")
                .build();
    }

    public Pet invalidEmpty() {
        return new Pet(); // intentionally incomplete
    }
}
