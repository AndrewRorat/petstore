package business.common.client;

import business.pet.PetClient;
import business.store.StoreClient;
import business.user.UserClient;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

@Getter
public class PetstoreClient {
    private final StoreClient store;
    private final PetClient pet;
    private final UserClient user;

    public PetstoreClient() {
        this.store = new StoreClient();
        this.pet = new PetClient();
        this.user = new UserClient();
    }
}
