package dev.abdelrahman.movies.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.abdelrahman.movies.Models.User.Role;
import dev.abdelrahman.movies.Models.User.User;
import dev.abdelrahman.movies.Models.User.DTOs.SignupDTO;
import dev.abdelrahman.movies.Repositories.UserRepository;
import dev.abdelrahman.movies.Utils.MessageResponse;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    public boolean existsByUsername(String username) { 
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String userEmail) {
        return userRepository.existsByEmail(userEmail);
    }

    public User createUser(SignupDTO signupDTO) {
        User user = new User();
        user.setUsername(signupDTO.getUsername());
        user.setEmail(signupDTO.getEmail());
        user.setPassword(encoder.encode(signupDTO.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);
        return user;
    } 
}
