package com.test.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Set;

/**
 * Created by Павел on 22.08.2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CategoryDto {
    private Long id;
    private String name;
    private String image;
    private Long parentId;
    private Set<CategoryDto> children;

    public CategoryDto() {
    }
}
