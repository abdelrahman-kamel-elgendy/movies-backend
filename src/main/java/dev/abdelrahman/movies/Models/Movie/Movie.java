package dev.abdelrahman.movies.Models.Movie;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import dev.abdelrahman.movies.Models.Review.Review;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "movies")
public class Movie {
    @Id
    private String id;
    private String title;
    private String releaseData;
    private String trailerLink;
    private String poster;
    private List<String> genres;
    private List<String> backdrops;
    private boolean isActive;

    @DocumentReference
    private List<Review> reviewIds;
}