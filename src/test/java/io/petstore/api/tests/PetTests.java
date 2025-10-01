package io.petstore.api.tests;

import business.common.client.PetstoreClient;
import business.enums.PetStatusEnum;
import business.models.pet.Pet;
import business.factories.pet.PetFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

import static org.hamcrest.Matchers.*;

@Epic("Petstore")
@Feature("Pet")
public class PetTests {

    private PetstoreClient api;
    private PetFactory pets;

    @BeforeClass
    public void setUp() {
        this.api = new PetstoreClient();
        this.pets = new PetFactory();
    }

    @Test
    public void petCrudFlow() {
        Pet p = pets.availableDog("Rex");

        api.getPet().add(p).statusCode(200)
                .body("id", anyOf(equalTo(p.getId().intValue()), equalTo(p.getId().intValue())))
                .body("name", equalTo("Rex"));

        api.getPet().get(p.getId()).statusCode(200).body("name", equalTo("Rex"));

        p.setName("Rexy");
        p.setStatus("sold");
        api.getPet().update(p).statusCode(200)
                .body("name", equalTo("Rexy")).body("status", equalTo("sold"));

        api.getPet().delete(p.getId()).statusCode(200);
        api.getPet().get(p.getId()).statusCode(404);
    }

    @Test
    public void findByStatusAvailable() {
        api.getPet().findByStatus(PetStatusEnum.AVAILABLE.getName())
                .statusCode(200)
                .body("$", isA(List.class));
    }

    @Test
    public void uploadImage() {
        Pet p = pets.availableDog("Milo");
        api.getPet().add(p).statusCode(200);

        File img = new File("src/test/resources/images/pet.png"); // add a small file
        api.getPet().uploadImage(p.getId(), img)
                .statusCode(200)
                .body("message", containsString("File uploaded"));

        api.getPet().delete(p.getId()).statusCode(200);
    }

    @Test
    public void createPetInvalid() {
        api.getPet().add(pets.invalidEmpty())
                .statusCode(is(400));
    }
}

