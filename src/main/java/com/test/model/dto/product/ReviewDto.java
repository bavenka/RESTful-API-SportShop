package com.test.model.dto.product;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Павел on 17.08.2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewDto {
    private Long id;
    private String message;
    private Integer stars;
    private String author;

    public ReviewDto() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }
}
