package tests;

import static tools.Sender.sessionAndContentTypeJson;

import endpoints.User;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
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
@Feature("Пользователь")
@ExtendWith(SpringExtension.class)
@ContextConfiguration({"/datapools/user.xml", "/endpoints/petstore.xml"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//TODO: реализовать для генерации данных для валид тестов негативного характера
//@ExtendWith(SetUpUser.class)
//@ExtendWith(CleanUpUser.class)
public class UserTest {
  @Qualifier("pass_user")
  @Autowired
  User user;
  @BeforeEach
  public void setUp() {
    RestAssured.baseURI = System.getenv("BASE_URL");
    user.setRequestSpecification(sessionAndContentTypeJson(System.getenv("API_KEY")));
  }
  @Test
  @Order(0)
  @DisplayName("Logs user into the system")
  void should_LogsUserIntoTheSystem_ReturnLis4t(){
    Response response = user.login();
  }
  @Test
  @Order(1)
  @DisplayName("Create user")
  void should_CreateUser_ReturnList6(){
    Response response = user.create();
  }
  @Test
  @Order(2)
  @DisplayName("Creates array of users with given input array")
  void should_CreatesArrayOfUsersWithGivenInputArray_ReturnArray(){
    Response response = user.createWithArray();
  }
  @Test
  @Order(2)
  @DisplayName("Creates list of users with given input array")
  void should_CreatesListOfUsersWithGivenInputArray_ReturnList(){
    Response response = user.createWithList();
  }
  @Test
  @Order(2)
  @DisplayName("Get user by user name")
  void should_GetUserByUserName_ReturnUserData(){
    Response response = user.find();
  }
  @Test
  @Order(2)
  @DisplayName("Updated user")
  void should_UpdatedUser_ReturnUserData(){
    Response response = user.upload();
  }
  @Test
  @Order(3)
  @DisplayName("Delete user")
  void should_DeleteUser_ReturnSuccessMessage(){
    Response response = user.delete();
  }
  @Test
  @Order(4)
  @DisplayName("Logs out current logged in user session")
  void should_LogsOutCurrentLoggedInUserSession_ReturnList5(){
    Response response = user.logout();
  }
}
