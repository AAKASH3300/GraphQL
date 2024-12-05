package com.spring_boot.graphql.persistence.service;

import com.spring_boot.graphql.persistence.exception.MovieNotFoundException;
import com.spring_boot.graphql.persistence.model.Movie;
import com.spring_boot.graphql.persistence.model.MovieInput;
import com.spring_boot.graphql.persistence.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie addMovie(MovieInput movieInput) {

        Movie movie = new Movie(movieInput.title(),
                movieInput.studio(),
                movieInput.director(),
                movieInput.releaseYear(),
                movieInput.movieCast());

        return movieRepository.save(movie);
    }

    public Movie updateMovie(Integer id, MovieInput movieInput) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie with given ID not exist"));

        movie.setTitle(movieInput.title());
        movie.setMovieCast(movieInput.movieCast());
        movie.setDirector(movieInput.director());
        movie.setStudio(movieInput.studio());
        movie.setReleaseYear(movieInput.releaseYear());

        return movieRepository.save(movie);
    }

    public String deleteMovie(Integer id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie with given ID not exist"));
        this.movieRepository.delete(movie);
        return "Movie deleted with id " + id;
    }

}