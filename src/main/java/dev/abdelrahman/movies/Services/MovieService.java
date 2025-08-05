package dev.abdelrahman.movies.Services;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import dev.abdelrahman.movies.Models.Movie.CreateMovieDTO;
import dev.abdelrahman.movies.Models.Movie.Movie;
import dev.abdelrahman.movies.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> allMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> singleMovie(ObjectId id) {
        return movieRepository.findById(id);
    }

    public Optional<Movie> singleMovieByImdbId(String imdbId) {
        return movieRepository.findMovieByImdbId(imdbId);
    }

    public Movie createMovie(CreateMovieDTO createMovieDTO) {
        return movieRepository.insert(new Movie());
    }
}
