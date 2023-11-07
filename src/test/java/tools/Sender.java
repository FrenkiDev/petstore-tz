package tools;

import static io.restassured.RestAssured.given;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class Sender {
  public static RequestSpecification sessionAndContentTypeJson(String sessionCookie){
    return new RequestSpecBuilder()
        .addCookie("api_key", sessionCookie)
        .setContentType(ContentType.JSON)
        .build();
  }
  @Step("GET запрос - {path} ожидаем статус ответа - {statusCode}")
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
  @Step("POST запрос - {path} ожидаем статус ответа - {statusCode}")
  public static Response step_Post(RequestSpecification requestSpecification, Map<String, ?> header, String path, Object queryJson, String responseSchema, int statusCode) {
    return given()
        .redirects().follow(false)
        .spec(requestSpecification)
        .when()
        .log().all()
        .headers(header)
        .body(queryJson)
        .post(path)
        .then()
        .log().all()
        .statusCode(statusCode)
        .body(matchesJsonSchema(new File(responseSchema)))
        .extract().response();
  }
  @Step("POST запрос - {path} с файлом ипараметрами загрузки, ожидаем статус ответа - {statusCode}")
  public static Response step_Post(RequestSpecification requestSpecification, Map<String, ?> header, String path, Map<String, ?> formParam, String nameParamFile, File file, String filetype, String responseSchema, int statusCode) {
    return given()
        .redirects().follow(false)
        .spec(requestSpecification)
        .when()
        .log().all()
        .headers(header)
        .formParams(formParam)
        .multiPart(nameParamFile, file, filetype)
        .post(path)
        .then()
        .log().all()
        .statusCode(statusCode)
        .body(matchesJsonSchema(new File(responseSchema)))
        .extract().response();
  }
  @Step("PUT запрос - {path} ожидаем статус ответа - {statusCode}")
  public static Response step_Put(RequestSpecification requestSpecification, String path, Object queryJson, String responseSchema, int statusCode) {
    return given()
        .redirects().follow(false)
        .spec(requestSpecification)
        .when()
        .log().all()
        .body(queryJson)
        .put(path)
        .then()
        .log().all()
        .statusCode(statusCode)
        .body(matchesJsonSchema(new File(responseSchema)))
        .extract().response();
  }
  @Step("DELETE запрос - {path} с параметром {nameParam} = {valueParam}  ожидаем статус ответа - {statusCode}")
  public static Response step_Delete(RequestSpecification requestSpecification, String path, String nameParam, Object valueParam, int statusCode) {
    return given()
        .redirects().follow(false)
        .spec(requestSpecification)
        .queryParam(nameParam, valueParam)
        .when()
        .log().all()
        .delete(path)
        .then()
        .log().all()
        .statusCode(statusCode)
        .extract().response();
  }
  @Step("DELETE запрос - {path}  ожидаем статус ответа - {statusCode}")
  public static Response step_Delete(RequestSpecification requestSpecification, String path, int statusCode) {
    return given()
        .redirects().follow(false)
        .spec(requestSpecification)
        .when()
        .log().all()
        .delete(path)
        .then()
        .log().all()
        .statusCode(statusCode)
        .extract().response();
  }
}