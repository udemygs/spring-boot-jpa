package com.movie;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.movie.controller.MovieController;

@SpringBootTest
class MovieApplicationTests {
	
	@Autowired
	private MovieController movieController;

	@Test
	void contextLoads() {
		assertThat(movieController).isNotNull();
	}
	
}
