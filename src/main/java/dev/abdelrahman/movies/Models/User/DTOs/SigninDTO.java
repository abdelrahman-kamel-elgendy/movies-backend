package dev.abdelrahman.movies.Models.User.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SigninDTO {

    @NotBlank(message = "Username must not be Blank")
    @Size(min = 3, max = 20, message = "Username must be from 3 to 20 chars")
    private String username;
    
    @NotBlank(message = "Password not be Blank")
    private String password;
}
