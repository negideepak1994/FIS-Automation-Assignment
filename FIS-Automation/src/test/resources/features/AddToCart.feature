Feature: Add item to cart on eBay

  Scenario: Verify item can be added to Cart
    Given User launches the browser
    When User navigates to "https://www.ebay.com"
    And User searches for "book"
    And User clicks on the first search result
    And User adds the item to the cart
    Then Cart icon should show 1 item