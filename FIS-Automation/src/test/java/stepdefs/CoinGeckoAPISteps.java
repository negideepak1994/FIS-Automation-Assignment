package stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.ApiTestBase;

import java.util.List;
import java.util.Map;

public class CoinGeckoAPISteps extends ApiTestBase {
    private Response response;
    @When("I fetch Bitcoin details")
    public void fetchBitcoinDetails() {
        response = request.when().get();
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Then("the response should contain current price in USD, GBP, and EUR")
    public void validateCurrencies() {
        var prices = response.jsonPath().getMap("market_data.current_price");
        Assert.assertTrue(prices.containsKey("usd"));
        Assert.assertTrue(prices.containsKey("gbp"));
        Assert.assertTrue(prices.containsKey("eur"));
    }
    @Then("market cap and total volume should be present for USD, GBP, and EUR")
    public void validateMarketCapAndVolume() {
        Map<String, Object> marketCap = response.jsonPath().getMap("market_data.market_cap");
        Map<String, Object> totalVolume = response.jsonPath().getMap("market_data.total_volume");

        for (String currency : List.of("usd", "gbp", "eur")) {
            Assert.assertTrue(marketCap.containsKey(currency), "Market cap missing for " + currency);
            Assert.assertTrue(totalVolume.containsKey(currency), "Total volume missing for " + currency);
            Assert.assertNotNull(marketCap.get(currency), "Market cap is null for " + currency);
            Assert.assertNotNull(totalVolume.get(currency), "Total volume is null for " + currency);
        }
    }
    @Then("price change percentage in last 24 hours should be available")
    public void validatePriceChangePercentage() {
        Float priceChange24h = response.jsonPath().getFloat("market_data.price_change_percentage_24h");
        Assert.assertNotNull(priceChange24h, "Price change percentage for 24h is null");
    }
    @Then("homepage URL should be present and not empty")
    public void validateHomepageUrl() {
        String homepageUrl = response.jsonPath().getString("links.homepage[0]");
        Assert.assertNotNull(homepageUrl, "Homepage URL is null");
        Assert.assertFalse(homepageUrl.isEmpty(), "Homepage URL is empty");
    }
}
