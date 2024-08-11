package com.example.bank_app.repository;
import com.example.bank_app.model.Instruction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructionRepository extends JpaRepository<Instruction, Long> {
}
