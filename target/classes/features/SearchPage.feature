@search
Feature: Amazon Search
  As a User on Amazon website
  I want to be able to search products using the search functionality
  So that I can see the list of valid results

  Background: Pre-requisite
    Given User navigates to Amazon
    Then User should be on Amazon page

  Scenario: Searches for valid item
    Given User search for "iPhone"
    Then search results should contains "iPhone"

  Scenario: Searches for an invalid item
    Given User search for "adsfsfd"
    Then No results should be returned
    And Message "did not match any products" should be displayed

  Scenario: Search for an item and filter results
    Given User search for "iPhone"
    Then search results should contains "iPhone"
    When User Applies Category Filter "Mobile Phones & Smartphones"
    Then Results Should be Filtered with "Mobile Phones & Smartphones"

  Scenario Outline: User Search Results Sorting
    Given User search for "<SearchKeyword>"
    And Sort By "<SortBy>"
    Then Result should be Sorted By "<SortBy>"

    Examples: Results Sorting
      | SearchKeyword  | SortBy               |
      | Samsung Galaxy | Relevance            |
      | iPad 4         | Featured             |
      | iPhone 13      | Price: Low to High   |
      | Nokia Lumia    | Newest Arrivals      |
      | Apple Watch 4  | Relevance            |
      | Xbox one       | Avg. Customer Review |
      | PS4            | Release Date         |