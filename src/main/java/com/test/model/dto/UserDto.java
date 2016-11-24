package com.test.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


/**
 * Created by Павел on 17.09.2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String name;
    private AddressDto address;
    private Collection<? extends GrantedAuthority> roles;

    public UserDto() {
    }
}
