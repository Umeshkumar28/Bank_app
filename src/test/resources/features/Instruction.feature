Feature: Payment Instructions

  Scenario: Create payment instructions
    Given accounts with IDs "1234567890" and "0987654321" exist
    When I send a payment instruction to transfer "150.00" from account "1234567890" to account "0987654321"
    Then the response should contain a debit instruction with amount "150.00" and account "1234567890"
    And the response should contain a credit instruction with amount "150.00" and account "0987654321"
