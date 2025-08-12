package dev.abdelrahman.movies.Models.Tokens;

import java.time.Instant;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Getter
@Document(collection = "blacklisted_tokens")
public class TokenBlacklist {

    @Id
    private ObjectId id;
    private String token;
    private Instant expiryDate;

    public TokenBlacklist(String token, Instant expiryDate) {
        this.token = token; 
        this.expiryDate = expiryDate;
    }
}