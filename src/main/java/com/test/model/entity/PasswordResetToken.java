package com.test.model.entity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Павел on 28.09.2016.
 */
@Entity
@Table(name = "tokens")
public class PasswordResetToken extends BasicEntity{
    private String token;
    private Date expiration;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public PasswordResetToken() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
