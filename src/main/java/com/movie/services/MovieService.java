package com.movie.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.entity.Movie;
import com.movie.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;

	public Movie addMovie(Movie movie) {
		return movieRepository.save(movie);
	}

	public List<Movie> getMovies() {
		List<Movie> movies = new ArrayList<>();
		for (Movie movie : movieRepository.findAll()) {
			movies.add(movie);
		}
		return movies;
	}

	public Movie getMovieById(int id) {
		Optional<Movie> movie = movieRepository.findById(id);
		return movie.orElseThrow(() -> new EntityNotFoundException());
	}

	public void updateMovie(Movie movie) {
		movieRepository.save(movie);
	}

	public void deleteMovieById(int id) {
		movieRepository.deleteById(id);
	}

}
