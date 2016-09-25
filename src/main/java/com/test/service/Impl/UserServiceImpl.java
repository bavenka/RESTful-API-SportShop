package com.test.service.Impl;

import com.test.model.entity.User;
import com.test.model.dto.UserDto;
import com.test.repository.UserRepository;
import com.test.service.UserService;
import com.test.utils.ConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Павел on 17.09.2016.
 */
@Component
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDto save(UserDto userDto) throws Exception {
        if (userDto == null || userDto.getUsername() == null
                || userDto.getPassword() == null) {
            throw new Exception("Invalid user data!");
        }
        User user = ConverterUtils.toUser(userDto);
        User existingUser = userRepository.save(user);
        if (existingUser == null) {
            throw new Exception("User is not added!");
        }
        return userDto;
    }

    @Override
    public UserDto findOne(Long id) throws Exception {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new Exception("User is not found!");
        }
        return ConverterUtils.toUserDto(user);
    }

    @Override
    public UserDto update(UserDto userDto) throws Exception {
        if(userDto.getId() == null ||
                userDto.getPhone() == null){
            throw new Exception("Invalid user data!");
        }
        User user = userRepository.findOne(userDto.getId());
        if(user == null){
            throw new Exception("User is not found!");
        }
        user.setPhone(userDto.getPhone());
        User existingUser = userRepository.save(user);
        if (existingUser == null) {
            throw new Exception("User is not updated!");
        }
        return userDto;
    }

}
