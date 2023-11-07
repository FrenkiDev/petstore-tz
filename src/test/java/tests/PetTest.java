package tests;

import static java.lang.String.format;
import static tools.Sender.sessionAndContentTypeJson;

import endpoints.Pet;
import io.qameta.allure.Epic;
import io.qameta.allure.Link;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
@Link("https://petstore.swagger.io/#/pet")
@Epic("Зоомагазин->Домашний питомец->валидационные тесты")
@ExtendWith(SpringExtension.class)
@ContextConfiguration({"/datapools/pet.xml", "/endpoints/petstore.xml"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@ExtendWith(SetUp.class)
//@ExtendWith(CleanUp.class)
public class PetTest {
  @Qualifier("pass")
  @Autowired
  Pet dog;
  static {
    RestAssured.baseURI = System.getenv("BASE_URL");
    RestAssured.requestSpecification = sessionAndContentTypeJson(System.getenv("API_KEY"));
  }
  @Test
  @Order(0)
  @DisplayName("Add a new pet to the store")
  void should_SuccessAddaNewPetToTheStore_CompareIdAndNameNewDogFrenki(){
    Response response = dog.add();
    JsonPath jPath = response.jsonPath();
    int id = jPath.getInt("id");
    String name = jPath.getString("name");
    Assertions.assertEquals(dog.getId(), id);
    Assertions.assertEquals(dog.getName(), name);
  }
  @Test
  @Order(1)
  @DisplayName("Update an existing pet")
  void should_SuccessUpdateAnExistingPet__CompareIdAndNameUpdateNameDogFrenk(){
    dog.setName("Frenk");
    Response response = dog.update();
    JsonPath jPath = response.jsonPath();
    int id = jPath.getInt("id");
    String name = jPath.getString("name");
    Assertions.assertEquals(dog.getId(), id);
    Assertions.assertEquals(dog.getName(), name);
  }
  @Test
  @Order(2)
  @DisplayName("uploads an image")
  void sho_(){
    Path filePath = Paths.get(dog.getImage());
    String fileName = filePath.getFileName().toString();
    Response response = dog.uploadImage();
    JsonPath jPath = response.jsonPath();
    int code = jPath.getInt("code");
    String type = jPath.getString("type");
    String message = jPath.getString("message");
    Assertions.assertAll(
        () -> Assertions.assertEquals(200, code),
        () -> Assertions.assertEquals("unknown", type),
        () -> Assertions.assertEquals(format("additionalMetadata: %s\nFile uploaded to ./%s, 116435 bytes", dog.getAdditionalMetadata(), fileName), message));
  }
  @Test
  @Order(2)
  @DisplayName("Finds Pets by status")
  void sho1_(){}
  @Test
  @Order(2)
  @DisplayName("Find pet by ID")
  void sho_1(){}
  @Test
  @Order(2)
  @DisplayName("Updates a pet in the store with form data")
  void sho_2(){}
  @Test
  @Order(7)
  @DisplayName("Delete a pet")
  void should_SuccessDeleteAPet_ReturnCode200And_TypeUnknownMessage100(){
    Response response = dog.delete();
    JsonPath jPath = response.jsonPath();
    int code = jPath.getInt("code");
    String type = jPath.getString("type");
    String message = jPath.getString("message");
    Assertions.assertAll(
        () -> Assertions.assertEquals(200, code),
        () -> Assertions.assertEquals("unknown", type),
        () -> Assertions.assertEquals("100", message));
  }
}