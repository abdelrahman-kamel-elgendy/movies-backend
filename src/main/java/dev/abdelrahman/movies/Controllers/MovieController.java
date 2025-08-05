package dev.abdelrahman.movies.Controllers;

import dev.abdelrahman.movies.Models.Movie.CreateMovieDTO;
import dev.abdelrahman.movies.Models.Movie.Movie;
import dev.abdelrahman.movies.Services.MovieService;
import jakarta.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return new ResponseEntity<List<Movie>>(movieService.allMovies(), HttpStatus.OK);
    }

    @GetMapping("/valid")
    public ResponseEntity<List<Movie>> getAllVaidMovies() {
        return new ResponseEntity<List<Movie>>(movieService.allValidMovies(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Movie>> getMovie(@PathVariable ObjectId id) {
        return new ResponseEntity<Optional<Movie>>(movieService.singleMovie(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@Valid @RequestBody CreateMovieDTO createMovieDTO) {
        return new ResponseEntity<Movie>(movieService.createMovie(createMovieDTO), HttpStatus.CREATED);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Movie> smootheDeleteMovie(@PathVariable ObjectId id) {
        return new ResponseEntity<Movie>(movieService.smootheDeleteMovie(id), HttpStatus.OK);
    } 

    @PutMapping("/active/{id}")
    public ResponseEntity<Movie> activeMovie(@PathVariable ObjectId id) {
        return new ResponseEntity<Movie>(movieService.activeMovie(id), HttpStatus.OK);
    } 

    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> DeleteMovie(@PathVariable ObjectId id) {
        return new ResponseEntity<Movie>(movieService.deleteMovie(id), HttpStatus.OK);
    } 
}
