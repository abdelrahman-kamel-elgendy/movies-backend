package dev.abdelrahman.movies.Repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dev.abdelrahman.movies.Models.User.User;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    Optional<User> findByUsername(String username); 
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
