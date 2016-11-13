package com.test.model.dto;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Павел on 12.08.2016.
 */
@Data
public class RoleDto {
    private Long id;
    private String name;

    public RoleDto() {
    }
}
