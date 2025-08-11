package dev.abdelrahman.movies.Controllers;

import dev.abdelrahman.movies.Controllers.Crud.CrudController;
import dev.abdelrahman.movies.Models.Review.Review;
import dev.abdelrahman.movies.Models.Review.DTOs.CreateReviewDTO;
import dev.abdelrahman.movies.Models.Review.DTOs.RetrieveReviewDTO;
import dev.abdelrahman.movies.Models.Review.DTOs.UpdateReviewDTO;
import dev.abdelrahman.movies.Services.ReviewService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController extends CrudController<ReviewService, Review, RetrieveReviewDTO, CreateReviewDTO, UpdateReviewDTO> {

}