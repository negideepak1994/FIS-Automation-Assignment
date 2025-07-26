package stepdefs;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.EbayPage;
import pages.EbayProductPage;
import utils.Log;

public class AddToCartSteps {

    WebDriver driver;
    EbayPage ebayPage;
    EbayProductPage productPage;

    @Given("User launches the browser")
    public void launchBrowser() {
        driver = Hooks.getDriverInstance();
        ebayPage = new EbayPage(driver);
        productPage = new EbayProductPage(driver);
    }

    @When("User navigates to {string}")
    public void navigateTo(String url) {
        driver.get(url);
        Log.info("Navigated to: " + url);
    }

    @When("User searches for {string}")
    public void searchFor(String item) {
        ebayPage.searchForItem(item);
    }

    @When("User clicks on the first search result")
    public void clickFirstResult() {
        ebayPage.clickFirstResultAndSwitchToNewTab();
    }

    @When("User adds the item to the cart")
    public void addToCart() {
        productPage.addItemToCart();
    }

    @Then("Cart icon should show {int} item")
    public void verifyCartCount(int expectedCount) {
        int actualCount = productPage.getCartCount();
        Assert.assertEquals(actualCount, expectedCount, "Cart count mismatch");

        String tooltip = productPage.getCartTooltipText();
        Assert.assertTrue(tooltip.contains(String.valueOf(expectedCount)),
                "Tooltip does not reflect correct item count: " + tooltip);
    }

}
