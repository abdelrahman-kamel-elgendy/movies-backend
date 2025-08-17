package dev.abdelrahman.movies.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dev.abdelrahman.movies.Models.Review.Review;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
}
