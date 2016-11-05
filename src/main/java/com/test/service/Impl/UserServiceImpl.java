package com.test.service.Impl;

import com.test.model.entity.User;
import com.test.model.dto.UserDto;
//import com.test.repository.PasswordResetTokenRepository;
import com.test.repository.UserRepository;
import com.test.service.UserService;
import com.test.utils.Constant;
import com.test.utils.Converter;
import lombok.NonNull;
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
    public UserDto save(@NonNull UserDto userDto) throws Exception {
        if (userDto.getUsername() == null
                || userDto.getPassword() == null || userDto.getPhone() == null) {
            throw new Exception(Constant.MESSAGE_INVALID_DATA);
        }
        if (userRepository.findByUsername(userDto.getUsername()) != null) {
            throw new Exception("Username "
                    + userDto.getUsername()
                    + Constant.MESSAGE_EXIST
            );
        }
        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new Exception("Email "
                    + userDto.getEmail()
                    + Constant.MESSAGE_EXIST
            );
        }
        User user = Converter.toUser(userDto);
        userRepository.save(user);
        return userDto;
    }

    @Override
    public UserDto findOne(@NonNull Long id) throws Exception {
        User user = userRepository.findOne(id);
        if (user == null) {
            return null;
        }
        return Converter.toUserDto(user);
    }

    @Override
    public UserDto update(@NonNull UserDto userDto) throws Exception {
        if (userDto.getId() == null ||
                userDto.getPhone() == null) {
            throw new Exception(Constant.MESSAGE_INVALID_DATA);
        }
        User user = userRepository.findOne(userDto.getId());
        if (user == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_USER);
        }
        user.setPhone(userDto.getPhone());
        userRepository.save(user);
        return userDto;
    }

}
