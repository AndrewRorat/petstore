package business.user;

import business.common.client.BaseClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class UserClient extends BaseClient {

    private static final String USER_ENDPOINT = "/user";

    @Step("Create user")
    public ValidatableResponse create(User body) {
        log.info("Creating a new user: {}", body);
        return getSpec().body(body).when().post(USER_ENDPOINT).then();
    }

    @Step("Get user by username: {username}")
    public ValidatableResponse getByUsername(String username) {
        log.info("Fetching user by username: {}", username);
        return getSpec().when().get(USER_ENDPOINT + "/{username}", username).then();
    }

    @Step("Update user: {username}")
    public ValidatableResponse update(String username, User body) {
        log.info("Updating user by username: {}", username);
        return getSpec().body(body).when().put(USER_ENDPOINT + "/{username}", username).then();
    }

    @Step("Delete user: {username}")
    public ValidatableResponse delete(String username) {
        log.info("Delete user by username: {}", username);
        return getSpec().when().delete(USER_ENDPOINT + "/{username}", username).then();
    }

    @Step("Login user: {userName}")
    public ValidatableResponse login(String userName, String password) {
        log.info("Logging in user with username: {}", userName);
        return getSpec().when().get(USER_ENDPOINT + "/login?username={u}&password={p}", userName, password).then();
    }

    @Step("Logout user")
    public ValidatableResponse logout() {
        log.info("Logging out current user");
        return getSpec().when().get(USER_ENDPOINT + "/logout").then();
    }

    @Step("Create users with list")
    public ValidatableResponse createWithList(List<User> users) {
        log.info("Creating multiple users with a list: {}", users);
        return getSpec().body(users).when().post(USER_ENDPOINT + "/createWithList").then();
    }

    @Step("Create users with array")
    public ValidatableResponse createWithArray(User[] users) {
        log.info("Creating multiple users with an array: {}", (Object) users);
        return getSpec().body(users).when().post(USER_ENDPOINT + "/createWithArray").then();
    }
}
