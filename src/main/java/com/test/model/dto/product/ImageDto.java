package com.test.model.dto.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Created by Павел on 17.08.2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ImageDto {
    private Long id;
    private String image;

    public ImageDto() {
    }
}
