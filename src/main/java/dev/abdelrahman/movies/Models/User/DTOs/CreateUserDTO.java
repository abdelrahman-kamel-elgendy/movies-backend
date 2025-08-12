package dev.abdelrahman.movies.Models.User.DTOs;

import dev.abdelrahman.movies.Models.User.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;


@Getter
public class CreateUserDTO {
    private String fitstName;

    private String lastName;
    
    private String Phone;

    @NotBlank(message = "Username must not be Blank")
    @Size(min = 3, max = 20, message = "Username must be from 3 to 20 chars")
    private String username;

    @NotBlank(message = "Email must not be Blank")
    @Size(max = 50, message = "Email must be not more than 50 chars")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Role must not be Blank")
    private Role role;

    private String gender;
}
