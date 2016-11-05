package com.test.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created by Павел on 29.09.2016.
 */
@Data
public class PasswordResetTokenDto {
    private Long id;
    private String token;
    private Date expiration;

    public PasswordResetTokenDto() {
    }
}
