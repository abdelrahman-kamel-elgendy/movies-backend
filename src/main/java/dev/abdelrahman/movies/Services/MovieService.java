package dev.abdelrahman.movies.Services;

import org.springframework.stereotype.Service;

import dev.abdelrahman.movies.Controllers.Exceptions.ResourceNotFoundException;
import dev.abdelrahman.movies.Models.Movie.Movie;
import dev.abdelrahman.movies.Models.Movie.DTOs.CreateMovieDTO;
import dev.abdelrahman.movies.Models.Movie.DTOs.RetrieveMovieDTO;
import dev.abdelrahman.movies.Models.Movie.DTOs.UpdateMovieDTO;
import dev.abdelrahman.movies.Repositories.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

@Service
public class MovieService implements CrudService<Movie, RetrieveMovieDTO, CreateMovieDTO, UpdateMovieDTO>{
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Movie> all() {
        return movieRepository.findAll();
    }
    
    public List<Movie> allValid() {
        return mongoTemplate.find(new Query(Criteria.where("isActive").is(true)), Movie.class);
    }

    public Movie findById(String id) {
         Movie movie = movieRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id " + id));

        return movie;
    }
    
    public Movie smootheDelete(String id) {
        Movie movie = this.findById(id);
            
        movie.setActive(false);;
        movieRepository.save(movie);
        return movie;
    }

    public Movie active(String id) {
        Movie movie = this.findById(id);

        movie.setActive(true);
        movieRepository.save(movie);
        return movie;
    }

    public Movie delete(String id) {
        Movie movie = this.findById(id);

        movieRepository.deleteById(id);
        return movie;
    }

    public RetrieveMovieDTO create(CreateMovieDTO createMovieDTO) {
        Movie movie = new Movie();
        movie.setTitle(createMovieDTO.getTitle());
        movie.setReleaseData(createMovieDTO.getReleaseData());
        movie.setPoster(createMovieDTO.getPoster());
        movie.setActive(true);
        movie.setGenres(createMovieDTO.getGenres());
        movie.setBackdrops(createMovieDTO.getBackdrops());
        
        movie = movieRepository.save(movie); 
        return new RetrieveMovieDTO(movie.getId(), movie.getTitle(), movie.getReleaseData(), movie.getTrailerLink(), movie.getPoster(), movie.getGenres(), movie.getBackdrops());
    }

    public RetrieveMovieDTO update(UpdateMovieDTO updateMovieDTO, String id) {
        Movie movie = this.findById(id);


        if (updateMovieDTO.getTitle() != null) 
            movie.setTitle(updateMovieDTO.getTitle());
        if (updateMovieDTO.getReleaseData() != null) 
            movie.setReleaseData(updateMovieDTO.getReleaseData());
        if (updateMovieDTO.getTrailerLink() != null) 
            movie.setTrailerLink(updateMovieDTO.getTrailerLink());
        if (updateMovieDTO.getPoster() != null) 
            movie.setPoster(updateMovieDTO.getPoster());
        if (updateMovieDTO.getGenres() != null)
            movie.setGenres(updateMovieDTO.getGenres());
        if (updateMovieDTO.getBackdrops() != null)
            movie.setBackdrops(updateMovieDTO.getBackdrops());
        
        movie = movieRepository.save(movie);
        return new RetrieveMovieDTO(movie.getId(), movie.getTitle(), movie.getReleaseData(), movie.getTrailerLink(), movie.getPoster(), movie.getGenres(), movie.getBackdrops());
    }

}
