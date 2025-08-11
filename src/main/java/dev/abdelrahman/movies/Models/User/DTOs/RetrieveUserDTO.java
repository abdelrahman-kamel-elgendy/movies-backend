package dev.abdelrahman.movies.Models.User.DTOs;

import dev.abdelrahman.movies.Models.User.Gender;
import dev.abdelrahman.movies.Models.User.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RetrieveUserDTO {
    private String fitstName;
    private String lastName;
    private String username;
    private String email;
    private String Phone;
    private Role role;
    private Gender gender;
}
