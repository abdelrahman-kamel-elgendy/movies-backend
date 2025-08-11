package dev.abdelrahman.movies.Controllers;

import dev.abdelrahman.movies.Controllers.Crud.CrudController;
import dev.abdelrahman.movies.Models.Movie.Movie;
import dev.abdelrahman.movies.Models.Movie.DTOs.CreateMovieDTO;
import dev.abdelrahman.movies.Models.Movie.DTOs.RetrieveMovieDTO;
import dev.abdelrahman.movies.Models.Movie.DTOs.UpdateMovieDTO;
import dev.abdelrahman.movies.Services.MovieService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController extends CrudController<MovieService, Movie, RetrieveMovieDTO, CreateMovieDTO, UpdateMovieDTO> {
    
}
