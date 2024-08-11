package com.example.bank_app.controller;

import com.example.bank_app.service.InstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private InstructionService instructionService;

    @PostMapping("/send")
    public String createPaymentInstruction(@RequestBody PaymentRequest request) {
        return instructionService.createAndSendPaymentInstruction(
                request.getDebitAccountNumber(),
                request.getCreditAccountNumber(),
                request.getAmount()
        );
    }
}

class PaymentRequest {
    private String debitAccountNumber;
    private String creditAccountNumber;
    private double amount;

    public String getDebitAccountNumber() {
        return debitAccountNumber;
    }

    public void setDebitAccountNumber(String debitAccountNumber) {
        this.debitAccountNumber = debitAccountNumber;
    }

    public String getCreditAccountNumber() {
        return creditAccountNumber;
    }

    public void setCreditAccountNumber(String creditAccountNumber) {
        this.creditAccountNumber = creditAccountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
