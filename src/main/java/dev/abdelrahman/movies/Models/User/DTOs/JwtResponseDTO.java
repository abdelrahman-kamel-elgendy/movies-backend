package dev.abdelrahman.movies.Models.User.DTOs;

import dev.abdelrahman.movies.Models.User.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponseDTO {
    private String token;
    private String type = "Bearer";
    private String username;
    private String email;
    private Role role;
}