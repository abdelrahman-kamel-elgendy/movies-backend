package dev.abdelrahman.movies.Models.User.DTOs;

import dev.abdelrahman.movies.Models.User.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateUserDTO {
    @Size(min = 3, max = 20, message = "Firstname must be from 3 to 20 chars")
    private String fitstName;
    
    @Size(min = 3, max = 20, message = "Lastname must be from 3 to 20 chars")
    private String lastName;
    
    @Size(min = 3, max = 20, message = "Username must be from 3 to 20 chars")
    private String username;
    
    @Size(max = 50, message = "Email must be not more than 50 chars")
    @Email(message = "Email must be valid")
    private String email;
    
    private String Phone;
    
    private String password;
    
    private String passwordConfirmation;
    
    private Gender gender;
}
