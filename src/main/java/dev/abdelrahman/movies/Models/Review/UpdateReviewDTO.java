package dev.abdelrahman.movies.Models.Review;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReviewDTO {
    @NotBlank(message = "Review body must not be blank")
    private String reviewBody;
}
