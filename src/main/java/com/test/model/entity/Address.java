package com.test.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by Павел on 21.07.2016.
 */
@Embeddable
@Getter
@Setter
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
}
