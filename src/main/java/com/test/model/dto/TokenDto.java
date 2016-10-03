package com.test.model.dto;

import java.util.Date;

/**
 * Created by Павел on 29.09.2016.
 */
public class TokenDto {
    private Long id;
    private String token;
    private Date expiration;

    public TokenDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
}
