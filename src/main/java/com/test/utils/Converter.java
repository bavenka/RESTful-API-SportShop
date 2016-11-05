package com.test.utils;

import com.test.model.dto.PasswordResetTokenDto;
import com.test.model.entity.PasswordResetToken;
import com.test.model.entity.Role;
import com.test.model.entity.User;
import com.test.model.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Павел on 17.09.2016.
 */
public class Converter {
    public static User toUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setPhone(userDto.getPhone());
        if (userDto.getRoles() != null) {
            Set<GrantedAuthority> collection = userDto.getRoles().stream().collect(Collectors.toSet());
            Set<Role> roles = new HashSet<>();
            for (GrantedAuthority grantedAuthority : collection) {
                Role role = new Role();
                role.setName(grantedAuthority.getAuthority());
            }
            user.setRoles(roles);
        }
        return user;
    }

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setPhone(user.getPhone());
        if (user.getRoles() != null) {
            Set<Role> roles = user.getRoles();
            Set<GrantedAuthority> collection = new HashSet<>();
            for (Role role : roles) {
                collection.add(new SimpleGrantedAuthority(role.getName()));
            }
            userDto.setRoles(collection);
        }
        return userDto;
    }

    public static PasswordResetTokenDto toTokenDto(PasswordResetToken passwordResetToken) {
        PasswordResetTokenDto passwordResetTokenDto = new PasswordResetTokenDto();
        passwordResetTokenDto.setId(passwordResetToken.getId());
        passwordResetTokenDto.setToken(passwordResetToken.getToken());
        passwordResetTokenDto.setExpiration(passwordResetToken.getExpiration());
        return passwordResetTokenDto;
    }
}
