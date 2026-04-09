package com.augustas.financialtracker.service;

import com.augustas.financialtracker.dtos.TransactionRequestDto;
import com.augustas.financialtracker.dtos.TransactionResponseDto;
import com.augustas.financialtracker.entities.Category;
import com.augustas.financialtracker.entities.Transaction;
import com.augustas.financialtracker.entities.Users;
import com.augustas.financialtracker.repositories.TransactionRepository;
import com.augustas.financialtracker.services.TransactionService;
import com.augustas.financialtracker.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @Mock
    TransactionRepository transactionRepository;
    @Mock
    UserService userService;

    @InjectMocks
    TransactionService transactionService;

    @Test
    void createTransactionShouldCreateTransactionSuccessfully(){
        UUID id = UUID.randomUUID();
        Users user = new Users();
        user.setId(id);

        TransactionRequestDto request = new TransactionRequestDto();
        request.setDescription("hello");
        request.setCategory(Category.FOOD);
        request.setAmount(20.0);

        Mockito.when(userService.getUser(id)).thenReturn(Optional.of(user));
        Mockito.when(transactionRepository.save(Mockito.any(Transaction.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        TransactionResponseDto response = transactionService.createTransaction(request, id);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(request.getDescription(), response.getDescription());
        Assertions.assertEquals(request.getAmount(), response.getAmount());

        Mockito.verify(transactionRepository, Mockito.times(1)).save(Mockito.any(Transaction.class));
    }

    @Test
    void totalSpentShouldReturnTotalSpentSuccessfully(){

        var transaction = new Transaction();
        transaction.setId(UUID.randomUUID());
        transaction.setDescription("hello");
        transaction.setCategory(Category.FOOD);
        transaction.setAmount(20.0);

        var transaction2 = new Transaction();
        transaction2.setId(UUID.randomUUID());
        transaction2.setDescription("hello");
        transaction2.setCategory(Category.FOOD);
        transaction2.setAmount(20.0);

        var transactions = List.of(transaction, transaction2);

        Mockito.when(transactionRepository.findAll()).thenReturn(transactions);

        var total = transactionService.totalSpent();

        Assertions.assertEquals(40.0, total);
    }

    @Test
    void totalSpentThisMonthShouldReturnTotalSpentSuccessfully(){

        var startOfMonth = LocalDateTime.now()
                .withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);

        var currentTransaction = new Transaction();
        currentTransaction.setAmount(20.0);
        currentTransaction.setDate(startOfMonth.plusDays(1));

        Mockito.when(transactionRepository.findByDateAfter(Mockito.any(LocalDateTime.class)))
                .thenReturn(List.of(currentTransaction));

        Double total = transactionService.totalSpentThisMonth();

        Assertions.assertEquals(20.0, total);

        Mockito.verify(transactionRepository).findByDateAfter(Mockito.any(LocalDateTime.class));
    }


}
