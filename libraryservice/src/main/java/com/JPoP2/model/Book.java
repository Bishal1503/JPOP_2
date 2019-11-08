package com.JPoP2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "All details about the book")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
	@ApiModelProperty(notes = "The database generated book id", position = 1, example = "1")
	private Long id;

	@ApiModelProperty(notes = "Name of the book", position = 2, example = "The Alchemist")
	private String name;

	@ApiModelProperty(notes = "Author of the book", position = 3, example = "Paulo Coelho")
	private String author;

	@ApiModelProperty(notes = "Category of the book", position = 4, example = "Fiction")
	private String category;

	@ApiModelProperty(notes = "Publisher of the book", position = 5, example = "HarperCollins")
	private String publisher;

	@ApiModelProperty(notes = "Price of the book", position = 6, example = "160.00")
	private Double price;
}
