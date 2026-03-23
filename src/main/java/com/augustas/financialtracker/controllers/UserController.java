package com.augustas.financialtracker.controllers;

import com.augustas.financialtracker.dtos.UserRequestDto;
import com.augustas.financialtracker.entities.Users;
import com.augustas.financialtracker.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public Users createUser(
            @Valid @RequestBody UserRequestDto request
    ){
        return userService.createUser(request);
    }

    @GetMapping("/{id}")
    public Optional<Users> getUser(
            @PathVariable UUID id
    ){
        return userService.getUser(id);
    }
}
