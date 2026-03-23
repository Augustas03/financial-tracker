package com.augustas.financialtracker.services;

import com.augustas.financialtracker.dtos.TransactionRequestDto;
import com.augustas.financialtracker.dtos.TransactionResponseDto;
import com.augustas.financialtracker.entities.Transaction;
import com.augustas.financialtracker.exceptions.ResourceNotFoundException;
import com.augustas.financialtracker.exceptions.UserNotFoundException;
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
    private final UserService userService;

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

    public TransactionResponseDto createTransaction(TransactionRequestDto request, UUID id){

        var user = userService.getUser(id).orElse(null);
        var transaction = new Transaction();

        if(user != null){
            transaction.setDescription(request.getDescription());
            transaction.setAmount(request.getAmount());
            transaction.setDate(LocalDateTime.now());
            transaction.setCategory(request.getCategory());
            transaction.setUser(user);
            transactionRepository.save(transaction);
            return toDto(transaction);
        }

        throw new UserNotFoundException("User does not exist");
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<TransactionResponseDto> getAllTransactionsByUserId(UUID id) {

        var transactions = transactionRepository.findTransactionsForUser(id);

        return transactions.stream().map(this::toDto).toList();
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

    public TransactionResponseDto toDto(Transaction transaction) {
        TransactionResponseDto dto = new TransactionResponseDto();
        dto.setDescription(transaction.getDescription());
        dto.setAmount(transaction.getAmount());
        dto.setCategory(transaction.getCategory());

        if (transaction.getUser() != null) {
            dto.setUser(transaction.getUser().getId());
        }
        return dto;
    }

}
