package dev.abdelrahman.movies.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.abdelrahman.movies.Models.User.DTOs.JwtResponseDTO;
import dev.abdelrahman.movies.Models.User.DTOs.SigninDTO;
import dev.abdelrahman.movies.Models.User.DTOs.SignupDTO;
import dev.abdelrahman.movies.Services.AuthService;
import dev.abdelrahman.movies.Utils.MessageResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SigninDTO signinDTO) {
        try {
            JwtResponseDTO response = authService.authenticateUser(signinDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: " + e.getMessage()));
        }
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupDTO signupDTO) {
        if(authService.existsByUsername(signupDTO.getUsername()))
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Username is already taken!"));
        
        if(authService.existsByEmail(signupDTO.getEmail()))
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Email is already taken!"));
        
        if(!signupDTO.getPassword().equals(signupDTO.getPasswordConfirmation())) 
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Password confirmation does not match!"));
        
        return ResponseEntity.ok(authService.createUser(signupDTO));
    }
}