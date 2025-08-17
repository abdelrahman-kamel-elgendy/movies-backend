package dev.abdelrahman.movies.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dev.abdelrahman.movies.Models.Movie.Movie;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
}
