package com.spring_boot.graphql.persistence.controller;

import com.spring_boot.graphql.persistence.model.Movie;
import com.spring_boot.graphql.persistence.model.MovieInput;
import com.spring_boot.graphql.persistence.service.MovieService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @QueryMapping
    List<Movie> getAllMoviesHandler() {
        return movieService.getAllMovies();
    }

    @MutationMapping
    Movie addMovieHandler(@Argument MovieInput movieInput) {
        return movieService.addMovie(movieInput);
    }

    @MutationMapping
    Movie updateMovieHandler(@Argument Integer id,@Argument MovieInput movieInput) {
        return movieService.updateMovie(id,movieInput);
    }

    @MutationMapping
    String deleteMovieHandler(@Argument Integer id) {
        return movieService.deleteMovie(id);
    }

}
