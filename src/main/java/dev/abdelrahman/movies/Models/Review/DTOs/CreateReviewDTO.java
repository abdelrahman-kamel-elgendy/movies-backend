package dev.abdelrahman.movies.Models.Review.DTOs;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class CreateReviewDTO {
    @NotBlank(message = "Review body must not be blank")
    private String reviewBody;

    @NotBlank(message = "Id must not be blank")
    private String id;


    public CreateReviewDTO(String reviewBody, String id) {
        this.reviewBody = reviewBody;
        this.id = id;
    }
}
