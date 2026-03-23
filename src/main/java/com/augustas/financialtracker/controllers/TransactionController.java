package com.augustas.financialtracker.controllers;

import com.augustas.financialtracker.dtos.TransactionRequestDto;
import com.augustas.financialtracker.entities.Transaction;
import com.augustas.financialtracker.repositories.TransactionRepository;
import com.augustas.financialtracker.services.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction (
            @Valid @RequestBody TransactionRequestDto request
    ){
        return ResponseEntity.ok().body(transactionService.createTransaction(request));
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getTransactions(){

        List<Transaction> transactions = transactionService.getAllTransactions();

        return ResponseEntity.ok().body(transactions);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeTransaction (
            @PathVariable(name = "id") UUID id
    ){

        if(transactionService.getTransaction(id).isPresent()){
            transactionService.deleteTransaction(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/total")
    public Double getTotalSpent(){
        return transactionService.totalSpentThisMonth();
    }
}
