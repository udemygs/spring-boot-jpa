package com.movie.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.movie.entity.Movie;

public interface MovieRepository extends CrudRepository<Movie, Integer>{
	
}
