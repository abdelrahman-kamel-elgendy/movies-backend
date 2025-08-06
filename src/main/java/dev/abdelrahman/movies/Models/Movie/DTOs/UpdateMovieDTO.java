package dev.abdelrahman.movies.Models.Movie.DTOs;

import java.util.List;

import lombok.Getter;

@Getter
public class UpdateMovieDTO {
    private String title;
    private String releaseData;
    private String trailerLink;
    private String poster;
    private List<String> genres;
    private List<String> backdrops;
}
