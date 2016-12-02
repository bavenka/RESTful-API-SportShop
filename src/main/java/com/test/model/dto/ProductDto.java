package com.test.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Created by Павел on 17.08.2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
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
    private CategoryDto category;

    public ProductDto() {
    }
}