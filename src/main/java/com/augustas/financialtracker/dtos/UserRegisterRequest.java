package com.augustas.financialtracker.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRegisterRequest {

    @NotNull(message= "Name is required")
    public String name;
    @NotNull(message= "Email is required")
    public String email;
    @NotNull(message= "Password is required")
    public String password;
}
