package com.movie.exception;

public class MovieNotFound extends RuntimeException {

	private static final long serialVersionUID = 4431168344044170597L;

	private Integer id;

	public MovieNotFound(Integer id) {
		this.setId(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
