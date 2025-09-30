package business.store;

import business.common.client.BaseClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class StoreClient extends BaseClient {

    private static final String STORE_ENDPOINT = "/store";


    @Step("Get inventory")
    public ValidatableResponse getInventory() {
        log.info("Fetching store inventory");
        return getSpec().when().get(STORE_ENDPOINT + "/inventory").then();
    }

    @Step("Place order")
    public ValidatableResponse placeOrder(Order body) {
        log.info("Placing a new order: {}", body);
        return getSpec().body(body).when().post(STORE_ENDPOINT + "/order").then();
    }

    @Step("Get order by ID: {id}")
    public ValidatableResponse getOrderById(long id) {
        log.info("Fetching order with ID: {}", id);
        return getSpec().when().get(STORE_ENDPOINT + "/order/{orderId}", id).then();
    }

    @Step("Delete order by ID: {id}")
    public ValidatableResponse deleteOrder(long id) {
        log.info("Deleting order with ID: {}", id);
        return getSpec().when().delete(STORE_ENDPOINT + "/order/{orderId}", id).then();
    }
}
