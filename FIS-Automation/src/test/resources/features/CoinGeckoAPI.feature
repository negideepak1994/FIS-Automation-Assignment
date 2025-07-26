Feature: CoinGecko Bitcoin API

  Scenario: Validate Bitcoin market data
    When I fetch Bitcoin details
    Then the response should contain current price in USD, GBP, and EUR
    Then market cap and total volume should be present for USD, GBP, and EUR
    Then price change percentage in last 24 hours should be available
    Then homepage URL should be present and not empty