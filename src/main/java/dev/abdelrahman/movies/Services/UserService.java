package dev.abdelrahman.movies.Services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.abdelrahman.movies.Controllers.Exceptions.BadRequestException;
import dev.abdelrahman.movies.Controllers.Exceptions.ResourceNotFoundException;
import dev.abdelrahman.movies.Models.Tokens.PasswordResetToken;
import dev.abdelrahman.movies.Models.User.Role;
import dev.abdelrahman.movies.Models.User.User;
import dev.abdelrahman.movies.Models.User.DTOs.CreateUserDTO;
import dev.abdelrahman.movies.Models.User.DTOs.RetrieveUserDTO;
import dev.abdelrahman.movies.Models.User.DTOs.UpdatePasswordDTO;
import dev.abdelrahman.movies.Models.User.DTOs.UpdateUserDTO;
import dev.abdelrahman.movies.Repositories.PasswordResetTokenRepository;
import dev.abdelrahman.movies.Repositories.UserRepository;

@Service
public class UserService implements CrudService<User, RetrieveUserDTO, CreateUserDTO, UpdateUserDTO> {
    @Value("${app.base-url}")
    private String baseUrl;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordResetTokenRepository tokenRepository;
    

    public List<User> all() {
        return userRepository.findAll();
    }

    public List<User> allValid() {
        return mongoTemplate.find(new Query(Criteria.where("isActive").is(true)), User.class);
    }
    
    public User findById(String id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        
        return user;
    }

    public User findUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with email " + email));
        
        return user;
    }

    public User findUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with username " + username));
        
        return user;
    }
    
    public User smootheDelete(String id) {
        User user = this.findById(id);

        user.setActive(false);;
        userRepository.save(user);
        return user;
    }

    public User active(String id) {
        User user = this.findById(id);

        user.setActive(true);
        userRepository.save(user);
        return user;
    }

    public User delete(String id) {
        User User = this.findById(id);
         
        userRepository.deleteById(id);
        return User;
    }

    public RetrieveUserDTO create(CreateUserDTO createUserDTO) {
        Role role = Role.USER;

        if(userRepository.existsByEmail(createUserDTO.getEmail()))
            throw new BadRequestException("Email alrady exists!");

        if(userRepository.existsByUsername(createUserDTO.getUsername()))
            throw new BadRequestException("Username alrady taken!");

        if(createUserDTO.getRole().equalsIgnoreCase("ADMIN"))
            role = Role.ADMIN;

        String email = createUserDTO.getEmail();
        String username = createUserDTO.getUsername();
        String password = createUserDTO.getUsername()+"123";
        String firstName = createUserDTO.getFirstName();
        User user = new User(email, username, encoder.encode(password), role);
        user.setFitstName(firstName);
        user.setLastName(createUserDTO.getLastName());
        user = userRepository.save(user);

        emailService.sendWelcomeEmail(email, firstName, username, password);
       return new RetrieveUserDTO(user.getFitstName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getPhone(), user.getGender());
    }
    
    public RetrieveUserDTO update(UpdateUserDTO updateUserDTO, String id) {
        User user = this.findById(id);
        User existsUser = null;

        if(updateUserDTO.getEmail() != null && !updateUserDTO.getEmail().isBlank()) {
            String email = updateUserDTO.getEmail(); 
            
            try {
                if(existsUser == null)
                    existsUser = this.findUserByEmail(email);

                if(!existsUser.getId().equals(id));
                    throw new BadRequestException("Email is not avilable!");
            } catch(ResourceNotFoundException e) {
                user.setEmail(email);
            }
        }

        if(updateUserDTO.getUsername() != null && !updateUserDTO.getUsername().isBlank()) {
            String username = updateUserDTO.getUsername();

            try {
                if(existsUser == null)
                    existsUser = this.findUserByUsername(username);

                if(!existsUser.getId().equals(id))
                    throw new BadRequestException("Username is not avilable!");
            } catch(ResourceNotFoundException e) {
                user.setUsername(username);
            }
        }

        if (updateUserDTO.getFitstName() != null && !updateUserDTO.getFitstName().isBlank())
            user.setFitstName(updateUserDTO.getFitstName());

        if (updateUserDTO.getLastName() != null && !updateUserDTO.getLastName().isBlank())
            user.setLastName(updateUserDTO.getLastName());

        if (updateUserDTO.getPhone() != null && !updateUserDTO.getPhone().isBlank())
            user.setPhone(updateUserDTO.getPhone());

        if (updateUserDTO.getGender() != null)
            user.setGender(updateUserDTO.getGender());
            
        user = userRepository.save(user);        
        return new RetrieveUserDTO(user.getFitstName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getPhone(), user.getGender());
    }

    public RetrieveUserDTO updatePassword(UpdatePasswordDTO updatePasswordDTO, String id) {
        User user = this.findById(id);

        if(updatePasswordDTO.getOldPassword() == null || updatePasswordDTO.getOldPassword().isBlank()) 
            throw new BadRequestException("Password must not be empty");
            
        if(!encoder.matches(updatePasswordDTO.getOldPassword(), user.getPassword())) 
            throw new BadCredentialsException("Access denied!");
            
        if(!updatePasswordDTO.getNewPassword().equals(updatePasswordDTO.getConfirmPassword()))
            throw new BadRequestException("Password confirmation must match new password");
            
        if(encoder.matches(updatePasswordDTO.getNewPassword(), user.getPassword()))
            throw new BadRequestException("Password must not match old password");

        user.setPassword(encoder.encode(updatePasswordDTO.getNewPassword()));

        user = userRepository.save(user);        
        return new RetrieveUserDTO(user.getFitstName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getPhone(), user.getGender());
    }

    public String resetPasswordRequest(String email) {
        User user = this.findUserByEmail(email);

        String token = UUID.randomUUID().toString();
        Instant expiry = Instant.now().plusSeconds(15 * 60); // 15 mins

        tokenRepository.save(new PasswordResetToken(email, token, expiry));

        String resetLink = baseUrl + "/api/v1/auth/reset-password?token=" + token;
        emailService.sendPasswordResetEmail(email, user.getFitstName(), resetLink);

        return resetLink;
    }

    public String resetPassword(String token, String newPassword) {
        Optional<PasswordResetToken> tokenOpt = tokenRepository.findByToken(token);
        if (tokenOpt.isEmpty() || tokenOpt.get().getExpiryDate().isBefore(Instant.now()))
            throw new BadRequestException("Invalid or expired token");

        String email = tokenOpt.get().getEmail();
        User user = this.findUserByEmail(email);

        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);

        tokenRepository.delete(tokenOpt.get());

        return email;
    }
}