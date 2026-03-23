package com.augustas.financialtracker.services;

import com.augustas.financialtracker.dtos.UserRequestDto;
import com.augustas.financialtracker.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.augustas.financialtracker.entities.Users;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public Users createUser(UserRequestDto request){
        var user = new Users();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return userRepository.save(user);
    }

    public Optional<Users> getUser(UUID id){
        return userRepository.findById(id);
    }
}
