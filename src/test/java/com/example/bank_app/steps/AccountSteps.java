package com.example.bank_app.steps;
import com.example.bank_app.BankAppApplication;
import com.example.bank_app.model.Account;
import com.example.bank_app.service.AccountService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AccountSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;

    @Autowired
    private AccountService accountService;

    @Given("an account with ID {string} exists")
    public boolean an_account_with_ID_exists(String accountId) {
        if (accountService.getAccountDetails(accountId) != null) {
           return true;
        }
        return false;
    }

    @When("I request the account details for ID {string}")
    public ResponseEntity<String>  i_request_the_account_details_for_ID(String accountId) {
        return response = restTemplate.getForEntity("/accounts/" + accountId, String.class);
    }

    @Then("the response should contain currency {string}")
    public void the_response_should_contain_currency(String currency) {
        assertTrue(response.getBody().contains("\"currency\":\"" + currency + "\""));
    }

    @Then("the response should contain bank {string}")
    public void the_response_should_contain_bank(String bank) {
        assertTrue(response.getBody().contains("\"bank\":\"" + bank + "\""));
    }

    @Then("the response should contain branch {string}")
    public void the_response_should_contain_branch(String branch) {
        assertTrue(response.getBody().contains("\"branch\":\"" + branch + "\""));
    }
}

