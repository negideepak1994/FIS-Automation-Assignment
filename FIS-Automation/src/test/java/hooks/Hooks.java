package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.Scenario;
import utils.BaseTest;
import utils.Log;

public class Hooks extends BaseTest {
    public static WebDriver driver;

    @Before(order = 1)
    public void setUp(Scenario scenario) {
        Log.info("----- STARTING SCENARIO: " + scenario.getName() + " -----");
        initializeDriver();
        driver = super.getDriver();
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (driver != null) {
            quitDriver();
        }
        Log.info("----- ENDING SCENARIO: " + scenario.getName() + " -----");
    }

    public static WebDriver getDriverInstance() {
        return driver;
    }
}
