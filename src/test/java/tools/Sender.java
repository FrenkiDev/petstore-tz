package tools;

import static io.restassured.RestAssured.given;

import endpoints.EndpointsImpl;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class Sender {
  public static RequestSpecification sessionAndContentTypeJson(String sessionCookie){
    return new RequestSpecBuilder()
        .addCookie("SESSION", sessionCookie)
        .setContentType(ContentType.JSON)
        .build();
  }

  public static Response step_Get(RequestSpecification requestSpecification, String path, String responseSchema, int statusCode) {
    return given()
        .redirects().follow(false)
        .spec(requestSpecification)
        .when()
        .get(path)
        .then()
        .statusCode(statusCode)
        .body(matchesJsonSchema(new File(responseSchema)))
        .extract().response();
  }

  public static Response step_Post(RequestSpecification requestSpecification, String beanPath, Object queryJson, String responseSchema, int statusCode) {
    return given()
        .redirects().follow(false)
        .spec(requestSpecification)
        .when()
        .log().all()
        .body(queryJson)
        .post(beanPath)
        .then()
        .log().all()
        .statusCode(statusCode)
        .body(matchesJsonSchema(new File(responseSchema)))
        .extract().response();
  }
}
