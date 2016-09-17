package com.test.service.Impl;

import com.test.model.User;
import com.test.model.dto.UserDto;
import com.test.repository.UserRepository;
import com.test.service.UserService;
import com.test.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Convert;
import java.util.List;

/**
 * Created by Павел on 17.09.2016.
 */
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto addUser(UserDto userDto) throws Exception {
        if(userDto == null || userDto.getName().isEmpty()
                || userDto.getPassword().isEmpty()) {
            throw new Exception("Invalid user data!");
        }
        User user = Converter.toUser(userDto);
        userRepository.save(user);
        return userDto;
    }

    @Override
    public UserDto getUser(Long id) throws Exception {
        User user = userRepository.findOne(id);
        if(user == null){
            throw new Exception("User is not found!");
        }
        return Converter.toUserDto(user);
    }

}
