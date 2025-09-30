package business.pet;

import business.common.client.BaseClient;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class PetClient extends BaseClient {

    private static final String PET_ENDPOINT = "/pet";

    @Step("Add new pet")
    public ValidatableResponse add(Pet body) {
        log.info("Adding a new pet: {}", body);
        return getSpec().body(body).when().post(PET_ENDPOINT).then();
    }

    @Step("Get pet by ID: {id}")
    public ValidatableResponse get(long id) {
        log.info("Fetching pet with ID: {}", id);
        return getSpec().when().get(PET_ENDPOINT + "/{petId}", id).then();
    }

    @Step("Update pet")
    public ValidatableResponse update(Pet body) {
        log.info("Updating pet: {}", body);
        return getSpec().body(body).when().put(PET_ENDPOINT).then();
    }

    @Step("Delete pet by ID: {id}")
    public ValidatableResponse delete(long id) {
        log.info("Deleting pet with ID: {}", id);
        return getSpec().when().delete(PET_ENDPOINT + "/{petId}", id).then();
    }

    @Step("Find pets by status: {status}")
    public ValidatableResponse findByStatus(String status) {
        log.info("Finding pets with status: {}", status);
        return getSpec().when().get(PET_ENDPOINT + "/findByStatus?status={s}", status).then();
    }

    @Step("Upload image for pet ID: {id}")
    public ValidatableResponse uploadImage(long id, File file) {
        log.info("Uploading image for pet with ID: {}", id);
        return getSpec().multiPart("file", file)
                .contentType(ContentType.MULTIPART)
                .when().post(PET_ENDPOINT + "/{petId}/uploadImage", id).then();
    }
}
