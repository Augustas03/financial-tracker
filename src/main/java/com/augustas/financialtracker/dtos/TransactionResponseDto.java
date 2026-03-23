package com.augustas.financialtracker.dtos;

import com.augustas.financialtracker.entities.Category;
import lombok.Data;

import java.util.UUID;

@Data
public class TransactionResponseDto {

    public String description;

    public Category category;

    public Double amount;

    public UUID user;
}
