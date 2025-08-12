package dev.abdelrahman.movies.Models.Tokens;

import java.time.Instant;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Getter
@Document(collection = "password_reset_tokens")
public class PasswordResetToken {

    @Id
    private ObjectId id;
    private String email;
    private String token;
    private Instant expiryDate;

    public PasswordResetToken(String email, String token, Instant expiryDate) {
        this.email = email;
        this.token = token;
        this.expiryDate = expiryDate;
    }
}
