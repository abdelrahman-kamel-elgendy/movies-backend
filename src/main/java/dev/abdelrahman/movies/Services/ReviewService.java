package dev.abdelrahman.movies.Services;

import dev.abdelrahman.movies.Controllers.ResourceNotFoundException;
import dev.abdelrahman.movies.Models.Movie.Movie;
import dev.abdelrahman.movies.Models.Review.CreateReviewDTO;
import dev.abdelrahman.movies.Models.Review.Review;
import dev.abdelrahman.movies.Models.Review.UpdateReviewDTO;
import dev.abdelrahman.movies.Repositories.MovieRepository;
import dev.abdelrahman.movies.Repositories.ReviewRepository;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MovieRepository movieRepository;  


    public List<Review> allReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(ObjectId id) {
        return reviewRepository.findById(id);
    }

    public Review createReview(CreateReviewDTO createReviewDTO) {
        Movie movie = movieRepository.findMovieByImdbId(createReviewDTO.getImdbId())
            .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id "));
        
        Review review = reviewRepository.insert(new Review(createReviewDTO.getReviewBody()));
        movie.getReviewIds().add(review);
        movieRepository.save(movie);

        return review;
    }

    public Review updateReview(UpdateReviewDTO updateReviewDTO, ObjectId id) {
        Review review = reviewRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Review not found with id "));
            
        review.setBody(updateReviewDTO.getReviewBody());
        reviewRepository.save(review);
        return review;
    }
}
