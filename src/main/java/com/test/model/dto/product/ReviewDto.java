package com.test.model.dto.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Created by Павел on 17.08.2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ReviewDto {
    private Long id;
    private String message;
    private Integer stars;
    private String author;

    public ReviewDto() {
    }
}
