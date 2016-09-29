package com.test.service.Impl;

import com.test.model.dto.TokenDto;
import com.test.model.entity.PasswordResetToken;
import com.test.model.entity.User;
import com.test.model.dto.UserDto;
import com.test.repository.TokenRepository;
import com.test.repository.UserRepository;
import com.test.service.UserService;
import com.test.utils.ConstantUtils;
import com.test.utils.ConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Павел on 17.09.2016.
 */
@Component
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Value("${token.expiration}")
    private Long tokenExpiration;

    @Override
    public UserDto save(UserDto userDto) throws Exception {
        if (userDto == null || userDto.getUsername() == null
                || userDto.getPassword() == null || userDto.getPhone() == null) {
            throw new Exception(ConstantUtils.MESSAGE_INVALID_DATA);
        }
        if (userRepository.findByUsername(userDto.getUsername()) != null) {
            throw new Exception("Username " + userDto.getUsername() + ConstantUtils.MESSAGE_EXIST);
        }
        User user = ConverterUtils.toUser(userDto);
        User existingUser = userRepository.save(user);
        if (existingUser == null) {
            throw new Exception(ConstantUtils.MESSAGE_FAILED_REQUEST);
        }
        return userDto;
    }

    @Override
    public UserDto findOne(Long id) throws Exception {
        User user = userRepository.findOne(id);
        if (user == null) {
            return null;
        }
        return ConverterUtils.toUserDto(user);
    }

    @Override
    public UserDto update(UserDto userDto) throws Exception {
        if (userDto.getId() == null ||
                userDto.getPhone() == null) {
            throw new Exception(ConstantUtils.MESSAGE_INVALID_DATA);
        }
        User user = userRepository.findOne(userDto.getId());
        if (user == null) {
            throw new Exception(ConstantUtils.MESSAGE_NOT_FOUND_USER);
        }
        user.setPhone(userDto.getPhone());
        User existingUser = userRepository.save(user);
        if (existingUser == null) {
            throw new Exception(ConstantUtils.MESSAGE_FAILED_REQUEST);
        }
        return userDto;
    }

    @Override
    public PasswordResetToken createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(token);
        passwordResetToken.setExpiration(new Date(tokenExpiration));
        passwordResetToken.setUser(user);
        return tokenRepository.save(passwordResetToken);
    }

}
