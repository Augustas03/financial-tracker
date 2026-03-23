package com.augustas.financialtracker.controllers;

import com.augustas.financialtracker.dtos.TransactionRequestDto;
import com.augustas.financialtracker.entities.Transaction;
import com.augustas.financialtracker.repositories.TransactionRepository;
import com.augustas.financialtracker.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;

    @PostMapping
    public Transaction createTransaction (
            @RequestBody TransactionRequestDto request
    ){
        var transaction = new Transaction();
        transaction.setDescription(request.getDescription());
        transaction.setAmount(request.getAmount());
        transaction.setDate(LocalDateTime.now());
        transaction.setCategory(request.getCategory());

        transactionRepository.save(transaction);

        return transaction;
    }

    @GetMapping
    public List<Transaction> getTransactions(){
        return transactionRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public boolean removeTransaction (
            @PathVariable(name = "id") UUID id
    ){

        transactionRepository.deleteById(id);

        return true;
    }

    @GetMapping("/total")
    public Double getTotalSpent(){
        return transactionService.totalSpentThisMonth();
    }
}
