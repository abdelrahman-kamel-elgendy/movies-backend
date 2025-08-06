package dev.abdelrahman.movies.Models.User.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RetrieveUserDTO {
    private String username;
    private String email;
}
