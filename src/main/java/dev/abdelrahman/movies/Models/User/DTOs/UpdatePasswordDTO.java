package dev.abdelrahman.movies.Models.User.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdatePasswordDTO {
    @NotBlank(message = "Old password must not be Blank")
    private String oldPassword;

    @NotBlank(message = "Password must not be Blank")
    private String newPassword;
    
    @NotBlank(message = "Password confirmation must not be Blank")
    private String confirmPassword;    
}
