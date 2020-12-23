package com.movie.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.entity.Movie;
import com.movie.exception.MovieAlreadyExists;
import com.movie.exception.MovieNotFound;
import com.movie.repository.MovieRepository;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/v2")
@Api(produces = MediaType.APPLICATION_JSON_VALUE, value = "Users Manage Movie System Version 2.")
public class Movie2Controller {

	private final MovieRepository movieRepo;

	@Autowired
	public Movie2Controller(MovieRepository movieRepo) {
		this.movieRepo = movieRepo;
	}

	@GetMapping("/movies")
	public List<Movie> findAll() {
		List<Movie> movieList = new ArrayList<>();
		movieRepo.findAll().forEach(movie -> movieList.add(movie));
		return movieList;
	}

	@GetMapping("/movies/{id}")
	public Movie findById(@PathVariable Integer id) {
		Optional<Movie> movie = movieRepo.findById(id);
		if (movie.isPresent()) {
			return movie.get();
		}
		throw new MovieNotFound(id);
	}

	@DeleteMapping("/movies/{id}")
	public void delete(@PathVariable Integer id) {
		Optional<Movie> movie = movieRepo.findById(id);
		if (movie.isPresent()) {
			movieRepo.deleteById(id);
		} else {
			throw new MovieNotFound(id);
		}
	}

	@PostMapping("/movies")
	public Integer create(@RequestBody Movie movie) {
		try {
			return movieRepo.save(movie).getId();
		} catch (DataIntegrityViolationException e) {
			throw new MovieAlreadyExists(movie.getTitle());
		}
	}

	@PutMapping("/movies/{id}")
	public void update(@RequestBody Movie movie, @PathVariable Integer id) {
		Optional<Movie> found = movieRepo.findById(id);
		if (found.isPresent()) {
			Movie m = found.get();
			if (movie.getTitle() != null) {
				m.setTitle(movie.getTitle());
			}
			if (movie.getCategory() != null) {
				m.setCategory(movie.getCategory());
			}
			m.setStarRating(movie.getStarRating());
			movieRepo.save(m);
		} else {
			throw new MovieNotFound(id);
		}
	}
}
