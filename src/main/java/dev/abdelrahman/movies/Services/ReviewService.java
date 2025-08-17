package dev.abdelrahman.movies.Services;

import dev.abdelrahman.movies.Controllers.Exceptions.ResourceNotFoundException;
import dev.abdelrahman.movies.Models.Movie.Movie;
import dev.abdelrahman.movies.Models.Review.Review;
import dev.abdelrahman.movies.Models.Review.DTOs.CreateReviewDTO;
import dev.abdelrahman.movies.Models.Review.DTOs.RetrieveReviewDTO;
import dev.abdelrahman.movies.Models.Review.DTOs.UpdateReviewDTO;
import dev.abdelrahman.movies.Models.User.User;
import dev.abdelrahman.movies.Repositories.MovieRepository;
import dev.abdelrahman.movies.Repositories.ReviewRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ReviewService implements CrudService<Review, RetrieveReviewDTO, CreateReviewDTO, UpdateReviewDTO>{
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private MongoTemplate mongoTemplate;

    private void checkReviewOwnership(Review review) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findUserByUsername(currentUsername);

        if (!review.getUser().getId().equals(currentUser.getId()))
            throw new AccessDeniedException("Access Denied!");
    }

    public List<Review> all() {
        return reviewRepository.findAll();
    }

    public List<Review> allValid() {
        return mongoTemplate.find(new Query(Criteria.where("isActive").is(true)), Review.class);
    }

    public Review findById(String id) {
        Review review = reviewRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Review not found with id " + id));

        return review;
    }

    public RetrieveReviewDTO create(CreateReviewDTO createReviewDTO) {
        Movie movie = movieService.findById(createReviewDTO.getId());

        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findUserByUsername(currentUsername);
        
        Review review = new Review(createReviewDTO.getReviewBody(), true, currentUser);

        reviewRepository.insert(review);
        
        movie.getReviews().add(review);
        movieRepository.save(movie);

        return new RetrieveReviewDTO(review.getId(), review.getBody());
    }

    public RetrieveReviewDTO update(UpdateReviewDTO updateReviewDTO, String id) {
        Review review = this.findById(id);
        checkReviewOwnership(review);

        review.setBody(updateReviewDTO.getReviewBody());
        review = reviewRepository.save(review);

        return new RetrieveReviewDTO(review.getId(), review.getBody());
    }

    public Review smootheDelete(String id) {
        Review review = this.findById(id);
        checkReviewOwnership(review);
            
        review.setActive(false);;
        reviewRepository.save(review);
        return review;
    }

    public Review active(String id) {
        Review review = this.findById(id);
            
        review.setActive(true);
        reviewRepository.save(review);
        return review;
    }

    public Review delete(String id) {
         Review review = this.findById(id);
            
        reviewRepository.deleteById(id);
        return review;
    }

}
