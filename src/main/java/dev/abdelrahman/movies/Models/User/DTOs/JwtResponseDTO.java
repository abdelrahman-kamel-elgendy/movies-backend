package dev.abdelrahman.movies.Models.User.DTOs;

public class JwtResponseDTO {
    private String token;
    private String type = "Bearer";
    private String username;
    private String email;

    public JwtResponseDTO(String token, String username, String email) {
        this.token = token;
        this.username = username;
        this.email = email;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }  

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}