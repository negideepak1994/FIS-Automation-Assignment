package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;

import java.time.Duration;

public class EbayProductPage {

    private WebDriver driver;
    private By addToCartButton = By.xpath("//div[@class='vim vi-evo-row-gap']/ul/li[2]/div/a/span/span");
    private By popupCloseButton = By.xpath("//button[@aria-label='Close overlay']");
    private By cartIconWithTooltip = By.cssSelector("span.gh-cart__icon");
    private By cartCountNumber = By.cssSelector("div[class*=\"gh-cart\"] span span");


    public EbayProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addItemToCart() {
        Log.info("Clicking 'Add to cart' button");
        driver.findElement(addToCartButton).click();
        Log.info("Waiting for popup to appear...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(popupCloseButton));

        Log.info("Closing popup after adding to cart");
        closeButton.click();
    }

    public int getCartCount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement countSpan = wait.until(ExpectedConditions.visibilityOfElementLocated(cartCountNumber));
        String count = countSpan.getText().trim();
        return Integer.parseInt(count);
    }

    public String getCartTooltipText() {
        return driver.findElement(cartIconWithTooltip).getAttribute("aria-label").trim();

    }
}
