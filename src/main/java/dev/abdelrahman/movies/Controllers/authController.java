package dev.abdelrahman.movies.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.abdelrahman.movies.Models.User.User;
import dev.abdelrahman.movies.Models.User.DTOs.SigninDTO;
import dev.abdelrahman.movies.Models.User.DTOs.SignupDTO;
import dev.abdelrahman.movies.Services.AuthService;
import dev.abdelrahman.movies.Utils.MessageResponse;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/auth")
public class authController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody SigninDTO signinDTO) {
        
        return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupDTO signupDTO) {
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
        
        return new ResponseEntity<User>(authService.createUser(signupDTO), HttpStatus.CREATED);
    }

}
