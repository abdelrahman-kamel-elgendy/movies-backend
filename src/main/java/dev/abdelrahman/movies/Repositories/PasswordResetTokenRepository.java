package dev.abdelrahman.movies.Repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.abdelrahman.movies.Models.Tokens.PasswordResetToken;

public interface PasswordResetTokenRepository extends MongoRepository<PasswordResetToken, String> {
    Optional<PasswordResetToken> findByToken(String token);
}
