package com.test.model.dto.product;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Павел on 17.08.2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String image;
    private String color;
    private String country;
    private String code;
    private String brand;
    private Boolean isFavorite;
    private Set<String> sizes;

    private Set<ReviewDto> reviews;
    private Set<ImageDto> images;

    public ProductDto() {

    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public Set<String> getSizes() {
        return sizes;
    }

    public void setSizes(Set<String> sizes) {
        this.sizes = sizes;
    }

    public Set<ReviewDto> getReviews() {
        return reviews;
    }

    public void setReviews(Set<ReviewDto> reviews) {
        this.reviews = reviews;
    }

    public Set<ImageDto> getImages() {
        return images;
    }

    public void setImages(Set<ImageDto> images) {
        this.images = images;
    }
}