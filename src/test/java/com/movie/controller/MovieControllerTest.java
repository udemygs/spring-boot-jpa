package com.movie.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.movie.entity.Movie;
import com.movie.services.MovieService;

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerTest {

	@MockBean
	private MovieService service;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testAddMovie() throws Exception {
		Movie m = new Movie(1, "Star Wars", "Action", 4.5);
		mockMvc.perform(post("/v1/movies", 1).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(m))).andExpect(status().isOk());
	}

	@Test
	void testGetMovies() throws Exception {
		// Setup our mocked service
		Movie m1 = new Movie(1, "Jumanji1", "Action", 0.5);
		Movie m2 = new Movie(2, "Jumanji2", "Action", 1.5);
		doReturn(Lists.newArrayList(m1, m2)).when(service).getMovies();

		// Execute the GET request
		mockMvc.perform(get("/v1/movies"))
				// Validate the response code and content type
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))

				// Validate the returned fields
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].title", is("Jumanji1"))).andExpect(jsonPath("$[0].category", is("Action")))
				.andExpect(jsonPath("$[0].starRating", is(0.5))).andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].title", is("Jumanji2"))).andExpect(jsonPath("$[1].category", is("Action")))
				.andExpect(jsonPath("$[1].starRating", is(1.5)));
	}

	@Test
	void testGetMovieById() throws Exception {
		Movie m = new Movie(1, "Star Wars", "Action", 4.5);
		doReturn(m).when(service).getMovieById(1);

		mockMvc.perform(get("/v1/movies/{id}", 1)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.title", is("Star Wars"))).andExpect(jsonPath("$.category", is("Action")))
				.andExpect(jsonPath("$.starRating", is(4.5)));
	}

	@Test
	void testUpdateMovie() throws Exception {
		Movie m = new Movie(1, "Star Wars", "Action", 4.5);
		mockMvc.perform(
				put("/v1/movies").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(m)))
				.andExpect(status().isOk());
	}

	@Test
	void testDeleteMovie() throws Exception {
		mockMvc.perform(delete("/v1/movies").queryParam("id", "1")).andExpect(status().isOk());
	}
}
