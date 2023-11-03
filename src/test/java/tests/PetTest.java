package tests;

import static tools.Sender.sessionAndContentTypeJson;

import endpoints.Pet;
import io.qameta.allure.Epic;
import io.qameta.allure.Link;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Link("https://petstore.swagger.io/#/pet")
@Epic("Зоомагазин->Домашний питомец")
@ExtendWith(SpringExtension.class)
@ContextConfiguration({"/datapools/pet.xml", "/endpoints/petstore.xml"})
public class PetTest {
  @Qualifier("pass")
  @Autowired
  Pet dog;

  static {
    RestAssured.baseURI = "https://petstore.swagger.io/v2";
    RestAssured.requestSpecification = sessionAndContentTypeJson("");
  }

  @Test
  @DisplayName("Add a new pet to the store")
  void should_SuccessAddaNewPetToTheStore_ReturnCode200(){
    Response response = dog.add();
    JsonPath jPath = response.jsonPath();

    int id = jPath.getInt("id");
    String name = jPath.getString("name");

    Assertions.assertEquals(dog.getId(), id);
    Assertions.assertEquals(dog.getName(), name);
  }
}
