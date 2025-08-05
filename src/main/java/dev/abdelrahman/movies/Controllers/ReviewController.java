package dev.abdelrahman.movies.Controllers;

import dev.abdelrahman.movies.Models.Review.CreateReviewDTO;
import dev.abdelrahman.movies.Models.Review.Review;
import dev.abdelrahman.movies.Models.Review.UpdateReviewDTO;
import dev.abdelrahman.movies.Services.ReviewService;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        return new ResponseEntity<List<Review>>(reviewService.allReviews(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Review>> getReviewById(@PathVariable ObjectId id) {
        return new ResponseEntity<Optional<Review>>(reviewService.getReviewById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@Valid @RequestBody CreateReviewDTO createReviewDTO) {
        return new ResponseEntity<Review>(reviewService.createReview(createReviewDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@Valid @RequestBody UpdateReviewDTO updateReviewDTO, @PathVariable ObjectId id) {
        return new ResponseEntity<Review>(reviewService.updateReview(updateReviewDTO, id), HttpStatus.OK);
    }
}
