package com.augustas.financialtracker.services;

import com.augustas.financialtracker.dtos.TransactionRequestDto;
import com.augustas.financialtracker.entities.Transaction;
import com.augustas.financialtracker.exceptions.ResourceNotFoundException;
import com.augustas.financialtracker.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public Double totalSpentThisMonth(){

        var firstDayOfMonth = LocalDateTime.now()
                .withDayOfMonth(1)
                .withHour(0)
                .withMinute(0)
                .withSecond(0);

        return transactionRepository.findByDateAfter(firstDayOfMonth)
                .stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public Double totalSpent(){

        List<Transaction> transactions = transactionRepository.findAll();

        return transactions.stream().mapToDouble(Transaction::getAmount).sum();

    }

    public Transaction createTransaction(TransactionRequestDto request){
        var transaction = new Transaction();
        transaction.setDescription(request.getDescription());
        transaction.setAmount(request.getAmount());
        transaction.setDate(LocalDateTime.now());
        transaction.setCategory(request.getCategory());

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransaction(UUID id) {
        return transactionRepository.findById(id);
    }

    public void deleteTransaction(UUID id) {
        if(!transactionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Transaction not found");
        }
        transactionRepository.deleteById(id);
    }
}
