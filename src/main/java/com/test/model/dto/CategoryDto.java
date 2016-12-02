package com.test.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Created by Павел on 22.08.2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class CategoryDto {
    private Long id;
    private String name;
    private String image;
    private Long parentId;
    private Set<CategoryDto> children;

    public CategoryDto() {
    }
}
