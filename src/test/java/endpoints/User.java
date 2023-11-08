package endpoints;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
@Getter
@Setter
public class User {
  @Qualifier("endpointUser")
  @Autowired
  protected Endpoint endpoint;
  protected RequestSpecification requestSpecification;
  private int status_code;
  private int id;
  private String username;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String phone;
  private int userStatus;
  @Step("Создает массив пользователей с заданным входным массивом")
  public Response createWithArray() {
    return null;
  }
  @Step("Создает список пользователей с заданным входным массивом")
  public Response createWithList() {
    return null;
  }
  @Step("Обновленный пользователь")
  public Response upload() {
    return null;
  }
  @Step("Получить пользователя по имени пользователя")
  public Response find() {
    return null;
  }
  @Step("Удалить пользователя")
  public Response delete() {
    return null;
  }
  @Step("Авторизует пользователя в системе")
  public Response login() {
    return null;
  }
  @Step("Выход из текущего сеанса пользователя, вошедшего в систему")
  public Response logout() {
    return null;
  }
  @Step("Создать пользователя")
  public Response create() {
    return null;
  }
}