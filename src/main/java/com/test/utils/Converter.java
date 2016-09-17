package com.test.utils;

import com.test.model.User;
import com.test.model.dto.UserDto;

/**
 * Created by Павел on 17.09.2016.
 */
public class Converter {
    public static User toUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        return user;
    }
    
    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
