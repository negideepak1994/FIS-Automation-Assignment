Feature: Validate CoinGecko Bitcoin API

  Scenario: Verify response status for Bitcoin endpoint
    Given I hit the Bitcoin API
    Then the status code should be 200
    And the response should be logged