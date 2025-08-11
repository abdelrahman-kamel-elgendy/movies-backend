package dev.abdelrahman.movies.Models.User.DTOs;

import javax.management.relation.Role;

import lombok.Getter;

@Getter
public class UpdateUserDTO {
    private String username;
    private String email;
    private String password;
    private Role role;
}
