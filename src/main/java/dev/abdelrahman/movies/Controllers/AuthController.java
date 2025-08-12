package dev.abdelrahman.movies.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.abdelrahman.movies.Controllers.Exceptions.ApiResponse;
import dev.abdelrahman.movies.Models.User.DTOs.JwtResponseDTO;
import dev.abdelrahman.movies.Models.User.DTOs.SigninDTO;
import dev.abdelrahman.movies.Models.User.DTOs.SignupDTO;
import dev.abdelrahman.movies.Services.AuthService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse<JwtResponseDTO>> authenticateUser(@Valid @RequestBody SigninDTO signinDTO) {
        JwtResponseDTO jwtResponse = authService.authenticateUser(signinDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "Login successful", jwtResponse));
    }
    
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<?>> registerUser(@Valid @RequestBody SignupDTO signupDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "User registered successfully", authService.createUser(signupDTO)));
    }
}