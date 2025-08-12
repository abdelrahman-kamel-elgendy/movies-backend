package dev.abdelrahman.movies.Controllers;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.abdelrahman.movies.Config.Security.JwtUtils;
import dev.abdelrahman.movies.Controllers.Exceptions.ApiResponse;
import dev.abdelrahman.movies.Models.Tokens.PasswordResetToken;
import dev.abdelrahman.movies.Models.Tokens.TokenBlacklist;
import dev.abdelrahman.movies.Models.User.User;
import dev.abdelrahman.movies.Models.User.DTOs.JwtResponseDTO;
import dev.abdelrahman.movies.Models.User.DTOs.SigninDTO;
import dev.abdelrahman.movies.Models.User.DTOs.SignupDTO;
import dev.abdelrahman.movies.Repositories.PasswordResetTokenRepository;
import dev.abdelrahman.movies.Repositories.TokenBlacklistRepository;
import dev.abdelrahman.movies.Services.AuthService;
import dev.abdelrahman.movies.Services.EmailService;
import dev.abdelrahman.movies.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenBlacklistRepository blacklistRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<?>> logout(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (headerAuth == null || !headerAuth.startsWith("Bearer "))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(false, "No token found", null));

        String token = headerAuth.substring(7);
        Instant expiryDate = jwtUtils.getExpirationDateFromJwtToken(token).toInstant();

        blacklistRepository.save(new TokenBlacklist(token, expiryDate));
        return ResponseEntity.ok(new ApiResponse<>(true, "Logged out successfully", null));
        

    }

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
    
    @PostMapping("/forgot-password")
    public ResponseEntity<?> requestReset(@RequestParam String email) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Password reset email sent", userService.resetPasswordRequest(email)));
    }
    
    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<?>> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        return ResponseEntity.ok().body(new ApiResponse<>(true, "Password updated successfully", userService.resetPassword(token, newPassword)));
    }
}