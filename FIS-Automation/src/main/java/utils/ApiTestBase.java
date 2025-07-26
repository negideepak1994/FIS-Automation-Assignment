package utils;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

import static utils.ConfigReader.getProperty;

public class ApiTestBase {
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = getProperty("baseURI");
        RestAssured.basePath = getProperty("basePath") + "/" + getProperty("endpoint");
    }
}

