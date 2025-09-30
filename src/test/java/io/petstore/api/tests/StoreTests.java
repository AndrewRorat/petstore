package io.petstore.api.tests;

import business.common.client.PetstoreClient;
import business.store.Order;
import business.store.OrderFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsEqual.equalTo;

@Epic("Petstore")
@Feature("Store")
public class StoreTests {

    private PetstoreClient api;
    private OrderFactory orders;

    @BeforeClass
    public void setUp() {
        this.api = new PetstoreClient();
        this.orders = new OrderFactory();
    }

    @Test
    public void inventoryOk() {
        api.getStore().getInventory()
                .statusCode(200)
                .body("$", notNullValue());
    }

    @Test
    public void orderCrudFlow() {
        Order order = orders.valid();

        api.getStore().placeOrder(order)
                .statusCode(200)
                .body("id", anyOf(equalTo(order.getId().intValue()), equalTo(order.getId().intValue())));

        api.getStore().getOrderById(order.getId())
                .statusCode(200)
                .body("quantity", equalTo(order.getQuantity()))
                .body("status", isOneOf("placed"));

        api.getStore().deleteOrder(order.getId())
                .statusCode(is(200));

        api.getStore().getOrderById(order.getId())
                .statusCode(404)
                .body("message", containsString("Order not found"));
    }

    @Test
    public void createInvalidOrder() {
        api.getStore().placeOrder(orders.invalidQuantity())
                .statusCode(anyOf(is(400)));
    }
}
