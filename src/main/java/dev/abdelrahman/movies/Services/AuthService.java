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
import dev.abdelrahman.movies.Controllers.Exceptions.BadRequestException;
import dev.abdelrahman.movies.Controllers.Exceptions.ResourceNotFoundException;

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
        if(!signupDTO.getPassword().equals( signupDTO.getPasswordConfirmation()))
            throw new BadRequestException("Password confirmation does not match!");

        if(this.existsByEmail(signupDTO.getEmail()))
            throw new BadRequestException("Email is already exists!");

        if(this.existsByUsername(signupDTO.getUsername()))
            throw new BadRequestException("Username is already taken!");
        
        User user = new User();
        user.setUsername(signupDTO.getUsername());
        user.setEmail(signupDTO.getEmail());
        user.setPassword(encoder.encode(signupDTO.getPassword()));
        user.setRole(Role.USER);

        if(signupDTO.getFitstName() != null && !signupDTO.getFitstName().isEmpty())
            user.setFitstName(signupDTO.getFitstName());

        if(signupDTO.getLastName() != null && !signupDTO.getLastName().isEmpty())
            user.setLastName(signupDTO.getLastName());

        if(signupDTO.getGender() != null)
                user.setGender(signupDTO.getGender());

        if(signupDTO.getPhone() != null && !signupDTO.getPhone().isEmpty())
            user.setPhone(signupDTO.getPhone());

        userRepository.save(user);
        return new RetrieveUserDTO(user.getFitstName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getPhone(), user.getGender());
    }
    
    public JwtResponseDTO authenticateUser(SigninDTO signinDTO) {
        User user = userRepository.findByUsername(signinDTO.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signinDTO.getUsername(),
                        signinDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken((UserDetails) authentication.getPrincipal());  
        
        return new JwtResponseDTO(jwt, "Bearer", user.getUsername(), user.getEmail(), user.getRole());
    }
}