package dev.abdelrahman.movies.Models.Movie;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateMovieDTO {
    @NotBlank(message = "Movie title must not be blank")
    private String title;
    private String releaseData;
    private String trailerLink;
    @NotBlank(message = "Poster must not be blank")
    private String poster;
    private List<String> genres;
    private List<String> backdrops;

    public CreateMovieDTO(String title, String releaseDate, String poster, List<String> genres, List<String> backdrops) {
        this.title = title;
        this.releaseData = releaseDate;
        this.poster = poster;
        this.genres = genres;
        this.backdrops = backdrops;
    }
}
