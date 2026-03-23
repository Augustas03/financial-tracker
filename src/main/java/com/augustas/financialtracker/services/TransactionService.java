package com.augustas.financialtracker.services;

import com.augustas.financialtracker.entities.Transaction;
import com.augustas.financialtracker.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public Double totalSpentThisMonth(){

        List<Transaction> transactions = transactionRepository.findAll();

        return transactions.stream().mapToDouble(Transaction::getAmount).sum();

    }

}
