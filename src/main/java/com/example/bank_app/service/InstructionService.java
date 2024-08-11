package com.example.bank_app.service;
import com.example.bank_app.model.Account;
import com.example.bank_app.model.Instruction;
import com.example.bank_app.repository.InstructionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructionService {
    @Autowired
    private AccountService accountService;
    @Autowired
    private InstructionRepository instructionRepository;

    public String createAndSendPaymentInstruction(String debitAccountNumber, String creditAccountNumber, double amount) {
        Account debitAccount = accountService.getAccountDetails(debitAccountNumber);
        Account creditAccount = accountService.getAccountDetails(creditAccountNumber);

        if (debitAccount == null || creditAccount == null) {
            return "Failure: One or both account numbers are invalid.";
        }

        Instruction debitInstruction = new Instruction();
        debitInstruction.setAccountNumber(debitAccountNumber);
        debitInstruction.setCurrency(debitAccount.getCurrency());
        debitInstruction.setInstructionType("DEBIT");
        debitInstruction.setAmount(amount);
        debitInstruction.setBank(debitAccount.getBank());
        debitInstruction.setBranch(debitAccount.getBranch());

        Instruction creditInstruction = new Instruction();
        creditInstruction.setAccountNumber(creditAccountNumber);
        creditInstruction.setCurrency(creditAccount.getCurrency());
        creditInstruction.setInstructionType("CREDIT");
        creditInstruction.setAmount(amount);
        creditInstruction.setBank(creditAccount.getBank());
        creditInstruction.setBranch(creditAccount.getBranch());

        instructionRepository.save(debitInstruction);
        instructionRepository.save(creditInstruction);

        return "Success: Payment instructions created.";
    }
}

