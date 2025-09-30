package io.petstore.api.tests;

import business.common.client.PetstoreClient;
import business.user.User;
import business.user.UserFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.Matchers.*;

@Epic("Petstore")
@Feature("User")
public class UserTests {

    private PetstoreClient api;
    private UserFactory users;

    @BeforeClass
    public void setUp() {
        this.api = new PetstoreClient();
        this.users = new UserFactory();
    }

    @Test()
    public void userLifecycle() {
        String uname = "user_" + Math.abs(ThreadLocalRandom.current().nextLong());
        User base = users.basic(uname);

        api.getUser().create(base).statusCode(200).body("message", not(isEmptyOrNullString()));
        api.getUser().getByUsername(uname).statusCode(200).body("username", equalTo(uname));

        base.setFirstName("Jane");
        api.getUser().update(uname, base).statusCode(200);
        api.getUser().getByUsername(uname).statusCode(200)
                .body("firstName", equalTo("Jane"));

        api.getUser().login(uname, "Secret123!").statusCode(200)
                .body("message", containsString("logged in user session"));
        api.getUser().logout().statusCode(200);

        api.getUser().delete(uname).statusCode(anyOf(is(200), is(404)));
    }

    @Test()
    public void bulkCreateUsers() {
        User a = users.basic("bulkA_" + UUID.randomUUID());
        User b = users.basic("bulkB_" + UUID.randomUUID());

        api.getUser().createWithList(List.of(a, b)).statusCode(200);
        api.getUser().createWithArray(new User[]{
                users.basic("bulkA2_" + UUID.randomUUID()),
                users.basic("bulkB2_" + UUID.randomUUID())
        }).statusCode(200);
    }

    @Test()
    public void createInvalidUser() {
        api.getUser().create(users.invalid()).statusCode(is(400));
    }
}

