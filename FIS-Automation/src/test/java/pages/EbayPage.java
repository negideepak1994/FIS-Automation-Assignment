package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;

import java.time.Duration;
import java.util.List;

public class EbayPage {

    private WebDriver driver;

    //Locators
    private By searchBoxLocator = By.id("gh-ac");
    private By searchBtnLocator = By.id("gh-search-btn");
    private By searchResults = By.cssSelector("ul.srp-results > li.s-item");
    private By firstItemLink = By.cssSelector("a.s-item__link");

    public EbayPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchForItem(String itemName) {
        Log.info("Searching for item: " + itemName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(searchBoxLocator));
        searchBox.clear();
        searchBox.sendKeys(itemName);

        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(searchBtnLocator));
        searchBtn.click();
    }

    public void clickFirstResultAndSwitchToNewTab() {
        Log.info("Clicking on the first visible search result and switching to new tab");

        String originalWindow = driver.getWindowHandle();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        List<WebElement> links = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(firstItemLink));
        for (WebElement link : links) {
            if (link.isDisplayed() && link.isEnabled()) {
                link.click();
                break;
            }
        }

        wait.until(driver -> driver.getWindowHandles().size() > 1);
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }
}
