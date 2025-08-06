package dev.abdelrahman.movies.Models.Review.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RetrieveReviewDTO {
    private String id;
    private String body;
}
