package com.augustas.financialtracker.dtos;

import com.augustas.financialtracker.entities.Category;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionRequestDto {

    @NotNull
    public String description;
    @NotNull
    public Category category;
    @NotNull
    private Double amount;
}
