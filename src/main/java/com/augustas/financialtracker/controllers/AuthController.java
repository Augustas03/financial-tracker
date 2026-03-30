package com.augustas.financialtracker.controllers;

import com.augustas.financialtracker.dtos.UserLoginRequest;
import com.augustas.financialtracker.dtos.UserRegisterRequest;
import com.augustas.financialtracker.services.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser (
            @Valid @RequestBody UserRegisterRequest request
    ){
        try{
            var user = authService.registerUser(request);
            return ResponseEntity.ok().body(user);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(
            @Valid @RequestBody UserLoginRequest request
    ){
        try{
            var token = authService.loginUser(request);
            return ResponseEntity.ok().body(token);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
