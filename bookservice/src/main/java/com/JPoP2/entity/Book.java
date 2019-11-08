package com.JPoP2.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@ApiModel(description = "All details about the book")
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated book id", position = 1, example = "101")
    private Long id;

    @ApiModelProperty(notes = "Name of the book", position = 2, example = "The Forest of Enchantments")
    private String name;

    @ApiModelProperty(notes = "Author of the book", position = 3, example = "Chitra Banerjee Divakaruni")
    private String author;

    @ApiModelProperty(notes = "Category of the book", position = 4, example = "Fiction")
    private String category;

    @ApiModelProperty(notes = "Publisher of the book", position = 5, example = "HarperCollins")
    private String publisher;

    @ApiModelProperty(notes = "Price of the book", position = 6, example = "358.00")
    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
