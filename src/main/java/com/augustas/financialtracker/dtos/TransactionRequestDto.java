package com.augustas.financialtracker.dtos;

import com.augustas.financialtracker.entities.Category;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionRequestDto {

    @NotNull(message = "Description is required")
    public String description;
    @NotNull(message = "Category is required")
    public Category category;
    @NotNull(message = "Amount is required")
    private Double amount;
}
