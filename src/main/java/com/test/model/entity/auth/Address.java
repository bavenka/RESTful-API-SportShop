package com.test.model.entity.auth;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by Павел on 21.07.2016.
 */
@Embeddable
public class Address {
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private Integer zipcode;
    @Column(nullable = false)
    private String country;

    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String address) {
        this.street = address;
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

    public void setZipcode(Integer postcode) {
        this.zipcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
