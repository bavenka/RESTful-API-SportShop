package com.test.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

/**
 * Created by Павел on 05.09.2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class OrderDto {
    private Long id;
    private String status;
    private Date date;
    private Set<ProductDto> products;

    public OrderDto() {
    }
}
