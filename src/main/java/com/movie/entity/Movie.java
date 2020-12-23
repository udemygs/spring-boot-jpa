package com.movie.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "MOVIES", uniqueConstraints = @UniqueConstraint(columnNames = { "title" }))
public class Movie {

	@Id
	private int id;

	private String title;

	private String category;

	@Column(name = "starrating")
	@Range(min = (long) 0.5, max = (long) 5.0)
	private double starRating;

	public Movie() {
		// do nothing
	}

	public Movie(int id, String title, String category, double starRating) {
		this.id = id;
		this.title = title;
		this.category = category;
		this.starRating = starRating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getStarRating() {
		return starRating;
	}

	public void setStarRating(double starRating) {
		this.starRating = starRating;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", category=" + category + ", starRating=" + starRating + "]";
	}

}
