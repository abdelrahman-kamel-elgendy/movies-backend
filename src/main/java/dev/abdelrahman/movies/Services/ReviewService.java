package dev.abdelrahman.movies.Services;

import dev.abdelrahman.movies.Models.Movie.Movie;
import dev.abdelrahman.movies.Models.Review.Review;
import dev.abdelrahman.movies.Models.Review.DTOs.CreateReviewDTO;
import dev.abdelrahman.movies.Models.Review.DTOs.RetrieveReviewDTO;
import dev.abdelrahman.movies.Models.Review.DTOs.UpdateReviewDTO;
import dev.abdelrahman.movies.Repositories.MovieRepository;
import dev.abdelrahman.movies.Repositories.ReviewRepository;
import dev.abdelrahman.movies.Utils.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MongoTemplate mongoTemplate;


    public List<Review> allReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> allValidReviews() {
        return mongoTemplate.find(new Query(Criteria.where("isActive").is(true)), Review.class);
    }

    public Optional<Review> getReviewById(ObjectId id) {
        return reviewRepository.findById(id);
    }

    public RetrieveReviewDTO createReview(CreateReviewDTO createReviewDTO) {
        String id = createReviewDTO.getId();
        Movie movie = movieRepository.findById(new ObjectId(id))
            .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id " + id));

        
        Review review = reviewRepository.insert(new Review(createReviewDTO.getReviewBody(), true));

        movie.getReviewIds().add(review);
        movieRepository.save(movie);
        
        return new RetrieveReviewDTO(review.getId(), review.getBody());
    }

    public RetrieveReviewDTO updateReview(UpdateReviewDTO updateReviewDTO, ObjectId id) {
        Review review = reviewRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Review not found with id " + id));
            
        review.setBody(updateReviewDTO.getReviewBody());
        reviewRepository.save(review);
        return new RetrieveReviewDTO(review.getId(), review.getBody());
    }

    public Review smootheDeleteReview(ObjectId id) {
         Review review = reviewRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Review not found with id "));
            
        review.setActive(false);;
        reviewRepository.save(review);
        return review;
    }

    public Review activeReview(ObjectId id) {
         Review review = reviewRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Review not found with id " + id));
            
        review.setActive(true);
        reviewRepository.save(review);
        return review;
    }

    public Review deleteReview(ObjectId id) {
         Review review = reviewRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Review not found with id " + id));
            
        reviewRepository.deleteById(id);
        return review;
    }

}
