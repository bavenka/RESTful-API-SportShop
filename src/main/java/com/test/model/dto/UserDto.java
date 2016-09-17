package com.test.model.dto;

/**
 * Created by Павел on 17.09.2016.
 */
public class UserDto {
    private Long id;
    private String name;
    private String password;

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
