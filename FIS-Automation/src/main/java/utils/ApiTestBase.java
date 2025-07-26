package utils;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static utils.ConfigReader.getProperty;

public class ApiTestBase {
    protected RequestSpecification request;
    public ApiTestBase() {
        RestAssured.baseURI = getProperty("baseURI");
        RestAssured.basePath = getProperty("basePath") + "/" + getProperty("endpoint");

        request = RestAssured.given()
                .contentType("application/json")
                .accept("application/json");
    }
}

