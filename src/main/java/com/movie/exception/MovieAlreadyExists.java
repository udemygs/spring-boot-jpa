package com.movie.exception;

public class MovieAlreadyExists extends RuntimeException {

	private static final long serialVersionUID = -3628173034096796256L;
	
	private String title;

	public MovieAlreadyExists(String title) {
		this.setTitle(title);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
