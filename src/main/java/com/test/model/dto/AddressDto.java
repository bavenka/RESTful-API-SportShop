package com.test.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Created by Anastasia on 09.08.2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AddressDto {
    private String street;
    private String city;
    private Integer zipcode;
    private String country;

    public AddressDto() {
    }
}
