package dev.abdelrahman.movies.Models.Review;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class CreateReviewDTO {
    @NotBlank(message = "Review body must not be blank")
    private String reviewBody;

    @NotBlank(message = "imdbId must not be blank")
    private String imdbId;


    public CreateReviewDTO(String reviewBody, String imdbId) {
        this.reviewBody = reviewBody;
        this.imdbId = imdbId;
    }
}
