package dev.abdelrahman.movies.Services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.abdelrahman.movies.Controllers.Exceptions.BadRequestException;
import dev.abdelrahman.movies.Controllers.Exceptions.ResourceNotFoundException;
import dev.abdelrahman.movies.Models.User.User;
import dev.abdelrahman.movies.Models.User.DTOs.CreateUserDTO;
import dev.abdelrahman.movies.Models.User.DTOs.RetrieveUserDTO;
import dev.abdelrahman.movies.Models.User.DTOs.UpdatePasswordDTO;
import dev.abdelrahman.movies.Models.User.DTOs.UpdateUserDTO;
import dev.abdelrahman.movies.Repositories.UserRepository;

@Service
public class UserService implements CrudService<User, RetrieveUserDTO, CreateUserDTO, UpdateUserDTO>{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private PasswordEncoder encoder;

    public List<User> all() {
        return userRepository.findAll();
    }

    public List<User> allValid() {
        return mongoTemplate.find(new Query(Criteria.where("isActive").is(true)), User.class);
    }
    
    public User findById(ObjectId id) {
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
    
    public User smootheDelete(ObjectId id) {
        User user = this.findById(id);

        user.setActive(false);;
        userRepository.save(user);
        return user;
    }

    public User active(ObjectId id) {
        User user = this.findById(id);

        user.setActive(true);
        userRepository.save(user);
        return user;
    }

    public User delete(ObjectId id) {
        User User = this.findById(id);
         
        userRepository.deleteById(id);
        return User;
    }

    public RetrieveUserDTO create(CreateUserDTO createUserDTO) {
        if(!this.findUserByEmail(createUserDTO.getEmail()).equals(null))
            throw new BadRequestException("Email alrady exists!");

        if(!this.findUserByUsername(createUserDTO.getUsername()).equals(null))
            throw new BadRequestException("Username alrady taken!");

        User user = new User(
                createUserDTO.getEmail(), 
                createUserDTO.getUsername(), 
                encoder.encode(createUserDTO.getUsername()+"123"), 
                createUserDTO.getRole()
            );

        user = userRepository.save(user);
       return new RetrieveUserDTO(user.getFitstName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getPhone(), user.getRole(), user.getGender());
    }
    
    public RetrieveUserDTO update(UpdateUserDTO updateDTO, ObjectId id) {
        User user = this.findById(id);
        User existsUser = null;

        if(updateDTO.getEmail() != null && !updateDTO.getEmail().isBlank()) {
            String email = updateDTO.getEmail(); 
            
            try {
                if(existsUser == null)
                    existsUser = this.findUserByEmail(email);

                if(!existsUser.getId().equals(id));
                    throw new BadRequestException("Email is not avilable!");
            } catch(ResourceNotFoundException e) {
                user.setEmail(email);
            }
        }

        if(updateDTO.getUsername() != null && !updateDTO.getUsername().isBlank()) {
            String username = updateDTO.getUsername();

            try {
                if(existsUser == null)
                    existsUser = this.findUserByUsername(username);

                if(!existsUser.getId().equals(id))
                    throw new BadRequestException("Username is not avilable!");
            } catch(ResourceNotFoundException e) {
                user.setUsername(username);
            }
        }

        if (updateDTO.getFitstName() != null && !updateDTO.getFitstName().isBlank())
            user.setFitstName(updateDTO.getFitstName());

         if (updateDTO.getLastName() != null && !updateDTO.getLastName().isBlank())
            user.setLastName(updateDTO.getLastName());
        
        
        return new RetrieveUserDTO(user.getFitstName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getPhone(), user.getRole(), user.getGender());
    }
}
