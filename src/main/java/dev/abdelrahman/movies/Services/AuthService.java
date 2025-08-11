package dev.abdelrahman.movies.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.abdelrahman.movies.Models.User.Role;
import dev.abdelrahman.movies.Models.User.User;
import dev.abdelrahman.movies.Models.User.DTOs.JwtResponseDTO;
import dev.abdelrahman.movies.Models.User.DTOs.RetrieveUserDTO;
import dev.abdelrahman.movies.Models.User.DTOs.SigninDTO;
import dev.abdelrahman.movies.Models.User.DTOs.SignupDTO;
import dev.abdelrahman.movies.Repositories.UserRepository;
import dev.abdelrahman.movies.Config.Security.JwtUtils;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtUtils jwtUtils;

    public boolean existsByUsername(String username) { 
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String userEmail) {
        return userRepository.existsByEmail(userEmail);
    }

    public RetrieveUserDTO createUser(SignupDTO signupDTO) {
        User user = new User();
        user.setUsername(signupDTO.getUsername());
        user.setEmail(signupDTO.getEmail());
        user.setPassword(encoder.encode(signupDTO.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);
        return new RetrieveUserDTO(user.getUsername(), user.getEmail());
    }
    
    public JwtResponseDTO authenticateUser(SigninDTO signinDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signinDTO.getUsername(),
                        signinDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken((UserDetails) authentication.getPrincipal());
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();        
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return new JwtResponseDTO(jwt, user.getUsername(), user.getEmail());
    }
}