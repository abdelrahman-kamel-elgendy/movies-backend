package dev.abdelrahman.movies.Models.User.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignupDTO {
    @NotBlank(message = "Usernamemust not be Blank")
    @Size(min = 3, max = 20, message = "Username must be from 3 to 20 chars")
    private String username;

    @NotBlank(message = "Email not be Blank")
    @Size(max = 50, message = "Email must be not more than 50 chars")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password not be Blank")
    private String password;

    @NotBlank(message = "Password confirmation not be Blank")
    private String passwordConfirmation;
}
