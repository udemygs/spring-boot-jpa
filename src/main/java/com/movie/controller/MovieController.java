package com.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.entity.Movie;
import com.movie.services.MovieService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1")
@Api(produces = MediaType.APPLICATION_JSON_VALUE, value = "Users to manage movie system. ")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@RequestMapping(value = "/movies", method = RequestMethod.POST)
	@ApiOperation(httpMethod = "POST", value = "Create a new movie", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created a new movie"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
		movieService.addMovie(movie);
		return new ResponseEntity<Movie>(HttpStatus.OK);
	}

	@RequestMapping(value = "/movies", method = RequestMethod.GET)
	@ApiOperation(value = "View all movies", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved all movies"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	public List<Movie> getMovies() {
		return movieService.getMovies();
	}

	@RequestMapping(value = "/movies/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Retrieve specific movie with the supplied movie id", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved the movie with the supplied id"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	public ResponseEntity<Movie> getMovie(@PathVariable int id) {

		Movie movie = movieService.getMovieById(id);
		if (movie == null) {
			return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Movie>(movie, HttpStatus.OK);

	}

	@RequestMapping(value = "/movies", method = RequestMethod.PUT)
	@ApiOperation(value = "Update a movie information", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated movie information"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie) {
		movieService.updateMovie(movie);
		return new ResponseEntity<Movie>(HttpStatus.OK);
	}

	@RequestMapping(value = "/movies", method = RequestMethod.DELETE)
	@ApiOperation(value = "Deletes specific movie with the supplied movie id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deletes the specific movie"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	public void deleteMovie(@RequestParam int id) {
		movieService.deleteMovieById(id);
	}

}
