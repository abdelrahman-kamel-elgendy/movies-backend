package dev.abdelrahman.movies.Repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import dev.abdelrahman.movies.Models.Tokens.PasswordResetToken;

public interface PasswordResetTokenRepository extends MongoRepository<PasswordResetToken, ObjectId> {
    Optional<PasswordResetToken> findByToken(String token);
}
