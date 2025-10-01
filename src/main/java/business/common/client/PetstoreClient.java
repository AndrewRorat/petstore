package business.common.client;

import business.clients.pet.PetClient;
import business.clients.store.StoreClient;
import business.clients.user.UserClient;
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
