package dev.abdelrahman.movies.Models.Review.DTOs;

import org.bson.types.ObjectId;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class CreateReviewDTO {
    @NotBlank(message = "Review body must not be blank")
    private String reviewBody;

    @NotBlank(message = "Id must not be blank")
    private ObjectId id;


    public CreateReviewDTO(String reviewBody, ObjectId id) {
        this.reviewBody = reviewBody;
        this.id = id;
    }
}
