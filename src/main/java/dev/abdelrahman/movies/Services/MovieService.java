package dev.abdelrahman.movies.Services;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import dev.abdelrahman.movies.Controllers.ResourceNotFoundException;
import dev.abdelrahman.movies.Models.Movie.CreateMovieDTO;
import dev.abdelrahman.movies.Models.Movie.Movie;
import dev.abdelrahman.movies.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.query.Query;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Movie> allMovies() {
        return movieRepository.findAll();
    }

    public List<Movie> allValidMovies() {
        return mongoTemplate.find(new Query(Criteria.where("isActive").is(true)), Movie.class);
    }

    public Optional<Movie> singleMovie(ObjectId id) {
        return movieRepository.findById(id);
    }
    
    public Movie smootheDeleteMovie(ObjectId id) {
         Movie Movie = movieRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id "));
            
        Movie.setActive(false);;
        movieRepository.save(Movie);
        return Movie;
    }

    public Movie activeMovie(ObjectId id) {
         Movie Movie = movieRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id "));
            
        Movie.setActive(true);
        movieRepository.save(Movie);
        return Movie;
    }

    public Movie deleteMovie(ObjectId id) {
         Movie Movie = movieRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id "));
            
        movieRepository.deleteById(id);
        return Movie;
    }

    public Movie createMovie(CreateMovieDTO createMovieDTO) {
        Movie movie = new Movie();
        movie.setTitle(createMovieDTO.getTitle());
        movie.setReleaseData(createMovieDTO.getReleaseData());
        movie.setPoster(createMovieDTO.getPoster());
        movie.setActive(true);
        
        return movieRepository.save(movie);
    }

}
