package endpoints;

import static io.restassured.RestAssured.requestSpecification;
import static java.lang.String.format;
import static tools.Sender.step_Delete;
import static tools.Sender.step_Post;
import static tools.Sender.step_Put;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Getter
@Setter
public class Pet {
  @Qualifier("endpointPet")
  @Autowired
  protected Endpoint endpoint;
  private int status_code;
  private int id;
  private String name;
  private String status;
  private List<String> photoUrls;
  private HashMap<String, String> category;
  private HashMap<String, String> tags;
  private String image;
  private String additionalMetadata;
  @Step("Добавить нового питомца в хранилище")
  public Response add() {
    Map<String, Object> queryJson = request("create");
    return step_Post(requestSpecification, endpoint.getHeaderParams(), endpoint.getUrls().get("base"), queryJson, endpoint.getResponse().get("create_pass"), status_code);
  }
  @Step("Обновить данные питомца")
  public Response update() {
    Map<String, Object> queryJson = request("create");
    return step_Put(requestSpecification, endpoint.getUrls().get("base"), queryJson, endpoint.getResponse().get("create_pass"), status_code);
  }
  @Step("Удалить питомца из хранилища")
  public Response delete() {
    return step_Delete(requestSpecification, endpoint.getUrls().get("base") + "/" + this.id, status_code);
  }
  @Step("Обновляем картинку питомца через загрузку файла")
  public Response uploadImage() {
    Map<String, String> headers = endpoint.getHeaderParams();
    headers.replace("Content-Type", "multipart/form-data");
    Map<String, String> formParams = new HashMap<>();
    formParams.put("additionalMetadata", this.additionalMetadata);
    return step_Post(requestSpecification
        , headers
        , format(endpoint.getUrls().get("uploadAnImages"), this.id)
        , formParams
        , "file"
        ,  new File(this.image)
        , "image/jpeg"
        , endpoint.getResponse().get("uploadAnImages")
        , status_code);
  }
  @NotNull
  @Step("Заполняем запрос {request}")
  private Map<String, Object> request(String request) {
    JsonPath jsonPath = new JsonPath(new File(endpoint.getRequest().get(request)));
    Map<String, Object> queryJson = jsonPath.getMap("$");
    queryJson.replace("id", this.id);
    queryJson.replace("name", this.name);
    queryJson.replace("status", this.status);
    queryJson.replace("photoUrls", this.photoUrls);
    queryJson.replace("category", this.category);
    queryJson.replace("tag", this.tags);
    return queryJson;
  }
}