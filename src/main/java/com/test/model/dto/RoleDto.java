package com.test.model.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Павел on 12.08.2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RoleDto {
    private Long id;
    private String name;

    public RoleDto() {
    }
}
