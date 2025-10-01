package io.petstore.api.tests;

import business.common.client.PetstoreClient;
import business.enums.OrderStatusEnum;
import business.models.store.Order;
import business.factories.store.OrderFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
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
                .body("status", isOneOf(OrderStatusEnum.PLACED.getName()));

        api.getStore().deleteOrder(order.getId())
                .statusCode(is(200));

        api.getStore().getOrderById(order.getId())
                .statusCode(404)
                .body("message", containsString("Order not found"));
    }

    @Test(dataProvider = "statuses")
    public void createOrderWithDifferentStatuses(String status) {
        Order body = orders.valid();

        body.setStatus(status);
        api.getStore().placeOrder(body)
                .statusCode(200)
                .body("status", equalTo(status));

        api.getStore().getOrderById(body.getId())
                .statusCode(200)
                .body("status", equalTo(status));

        api.getStore().deleteOrder(body.getId()).statusCode(anyOf(is(200), is(204)));
    }

    @Test
    public void createInvalidOrder() {
        api.getStore().placeOrder(orders.invalidQuantity())
                .statusCode(anyOf(is(400)));
    }

    @Test
    public void createWithoutPetId() {
        Order order = orders.valid();
        order.setPetId(null);
        api.getStore().placeOrder(order)
                .statusCode(is(400));
    }

    @DataProvider
    public Object[][] statuses() {
        return new Object[][]{
                {OrderStatusEnum.PLACED.getName()},
                {OrderStatusEnum.APPROVED.getName()},
                {OrderStatusEnum.DELIVERED.getName()}
        };
    }
}
