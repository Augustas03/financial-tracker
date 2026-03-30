package com.augustas.financialtracker.services;

import com.augustas.financialtracker.dtos.UserLoginRequest;
import com.augustas.financialtracker.dtos.UserRegisterRequest;
import com.augustas.financialtracker.dtos.UserRegisterResponse;
import com.augustas.financialtracker.entities.Users;
import com.augustas.financialtracker.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserRegisterResponse registerUser(UserRegisterRequest request) throws Exception {

        if(userRepository.existsByEmail(request.getEmail())){
            throw new Exception("User with email already exists!");
        }

        var user = new Users();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        UserRegisterResponse dto = new UserRegisterResponse();

        dto.setName(user.getName());
        dto.setEmail(user.getEmail());

        return dto;
    }


    public String loginUser(UserLoginRequest request) throws Exception{

        var token = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        var newToken = authenticationManager.authenticate(token);

        var users = newToken.getPrincipal();

        if(users == null){
            throw new Exception("User not found");
        }

        return jwtService.generateToken((Users) users);
    }
}
