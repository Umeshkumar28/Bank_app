Feature: Account Details

  Scenario: Retrieve account details
    Given an account with ID "1234567890" exists
    When I request the account details for ID "1234567890"
    Then the response should contain currency "USD"
    And the response should contain bank "Bank of Example"
    And the response should contain branch "Main Branch"
