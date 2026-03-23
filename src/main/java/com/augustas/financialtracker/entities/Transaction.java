package com.augustas.financialtracker.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.augustas.financialtracker.entities.Users;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;

    private Double amount;

    private Category category;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

}
