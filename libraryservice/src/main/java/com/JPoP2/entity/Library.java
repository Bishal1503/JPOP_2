package com.JPoP2.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@ApiModel(description = "All details about the library")
@Getter
@Setter
public class Library {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "The database generated id", position = 1, example = "1")
	private Long id;

	@ApiModelProperty(notes = "Id of book borrowed by the user", position = 2, example = "1")
	@NotNull
	private Long bookId;

	@ApiModelProperty(notes = "If of user who borrow the book", position = 3, example = "1")
	@NotNull
	private Long userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
