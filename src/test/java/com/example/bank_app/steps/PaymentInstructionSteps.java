package com.example.bank_app.steps;
import com.example.bank_app.BankAppApplication;
import com.example.bank_app.service.AccountService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PaymentInstructionSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;

    @Autowired
    private AccountService accountService;

    @Given("accounts with IDs {string} and {string} exist")
    public boolean accounts_with_IDs_and_exist(String debitAccountId, String creditAccountId) {
        if ((accountService.getAccountDetails(debitAccountId) != null) &&(accountService.getAccountDetails(creditAccountId) != null))  {
            return true;
        }
        return false;
    }

    @When("I send a payment instruction to transfer {string} from account {string} to account {string}")
    public void i_send_a_payment_instruction_to_transfer_from_account_to_account(String amount, String debitAccountId, String creditAccountId) {
        String jsonBody = String.format("{\"debitAccountId\":\"%s\",\"creditAccountId\":\"%s\",\"amount\":%s}", debitAccountId, creditAccountId, amount);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);

        response = restTemplate.exchange("/payment-instructions", HttpMethod.POST, request, String.class);
    }

    @Then("the response should contain a debit instruction with amount {string} and account {string}")
    public void the_response_should_contain_a_debit_instruction_with_amount_and_account(String amount, String accountId) {
        assertTrue(response.getBody().contains("\"instructionType\":\"debit\""));
        assertTrue(response.getBody().contains("\"amount\":" + amount));
        assertTrue(response.getBody().contains("\"accountId\":\"" + accountId + "\""));
    }

    @Then("the response should contain a credit instruction with amount {string} and account {string}")
    public void the_response_should_contain_a_credit_instruction_with_amount_and_account(String amount, String accountId) {
        assertTrue(response.getBody().contains("\"instructionType\":\"credit\""));
        assertTrue(response.getBody().contains("\"amount\":" + amount));
        assertTrue(response.getBody().contains("\"accountId\":\"" + accountId + "\""));
    }
}
