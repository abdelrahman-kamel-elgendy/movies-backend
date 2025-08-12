package dev.abdelrahman.movies.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.abdelrahman.movies.Models.Tokens.TokenBlacklist;

public interface TokenBlacklistRepository extends MongoRepository<TokenBlacklist, String> {
    boolean existsByToken(String token);
}
