package com.test.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Anastasia on 09.08.2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDto {
    private String street;
    private String city;
    private Integer zipcode;
    private String country;

    public AddressDto() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
