package com.movie.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.movie.exception.MovieException.Response;

@ControllerAdvice
public class MovieControllerExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
		return new ResponseEntity<>(Response.fail("message taken from the exception"), HttpStatus.OK);
	}

	@ExceptionHandler(MovieAlreadyExists.class)
	public ResponseEntity<Object> handleTaskAlreadyExistsException(MovieAlreadyExists ex, WebRequest request) {
		return new ResponseEntity<>(Response.fail("Task with title: already exists"), HttpStatus.OK);
	}

	@ExceptionHandler(MovieNotFound.class)
	public ResponseEntity<Object> handleTaskNotFoundException(MovieNotFound ex, WebRequest request) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", "fail");
		body.put("message", "Movie Not Found for ID:" + ex.getId());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
}
