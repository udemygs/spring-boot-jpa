package com.movie.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.movie.entity.Movie;
import com.movie.repository.MovieRepository;
import com.movie.services.MovieService;

@SpringBootTest
public class MovieServiceTest {

	@Autowired
	private MovieService movieService;

	@MockBean
	private MovieRepository movieRepository;

	@Test
	public void testAddMovie() {
		Movie addMovie = new Movie(123, "King Kong", "Action", 4.0);
		doReturn(addMovie).when(movieRepository).save(Mockito.any());

		movieService.addMovie(addMovie);
	}

	@Test
	public void testGetMovie() {
		Movie m1 = new Movie(99, "Captain", "Action", 1);
		Movie m2 = new Movie(98, "Captain2", "Action", 1);
		doReturn(Arrays.asList(m1, m2)).when(movieRepository).findAll();

		List<Movie> returnMovies = movieService.getMovies();

		assertEquals(2, returnMovies.size());
	}

	@Test
	public void testGetMovieById() {
		// Setup our mock repository
		Movie movie = new Movie(11, "Captain", "Action", 1);
		doReturn(Optional.of(movie)).when(movieRepository).findById(11);

		// Execute the service call
		Movie returnedMovie = movieService.getMovieById(11);

		// Assert the response
		Assertions.assertSame(returnedMovie.getTitle(), "Captain");
	}

	@Test
	public void testGetMovieByIdNotFound() {
		Movie movie = new Movie(12, "Captain", "Action", 1);
		doReturn(Optional.of(movie)).when(movieRepository).findById(12);

		Movie returnedMovie = movieService.getMovieById(12);

		assertNotSame(returnedMovie.getTitle(), "Captain America");
	}

	@Test
	public void testUpdateMovie() {
		Movie movie = new Movie(11, "Captain", "Action", 1);

		doReturn(movie).when(movieRepository).save(movie);
		movieService.updateMovie(movie);
	}

	@Test
	public void testDeleteMovieById() {
		Movie movie = new Movie(11, "Captain", "Action", 1);
		doReturn(movie).when(movieRepository).deleteById(11);
		
		movieService.deleteMovieById(11);
	}

}
