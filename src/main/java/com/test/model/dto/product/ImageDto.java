package com.test.model.dto.product;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Павел on 17.08.2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageDto {
    private Long id;
    private String image;

    public ImageDto() {
    }

    public ImageDto(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
