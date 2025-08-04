package dev.abdelrahman.movies.Models;

import lombok.Data;
import lombok.Getter;
import org.bson.types.ObjectId;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "movies")
public class Movie {
    @Id
    private ObjectId id;
    private String imdbId;
    private String title;
    private String releaseData;
    private String trailerLink;
    private String posterLink;
    private List<String> genres;
    private List<String> backdrops;

    @DocumentReference
    private List<Review> reviewIds;

    public ObjectId getId() {
        return id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseData() {
        return releaseData;
    }

    public String getTrailerLink() {
        return trailerLink;
    }

    public String getPosterLink() {
        return posterLink;
    }

    public List<String> getGenres() {
        return genres;
    }

    public List<String> getBackdrops() {
        return backdrops;
    }

    public List<Review> getReviewIds() {
        return reviewIds;
    }
}