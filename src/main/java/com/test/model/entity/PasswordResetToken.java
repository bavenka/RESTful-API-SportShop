package com.test.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Павел on 28.09.2016.
 */
@Entity
@Getter
@Setter
@Table(name = "password_reset_tokens")
public class PasswordResetToken extends BasicEntity {
    private String token;
    private Date expiration;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public PasswordResetToken() {
    }
}
