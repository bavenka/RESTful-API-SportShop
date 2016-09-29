package com.test.service;

import com.test.model.dto.TokenDto;
import com.test.model.dto.UserDto;
import com.test.model.entity.PasswordResetToken;
import com.test.model.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Павел on 17.09.2016.
 */
@Service
public interface UserService {
    UserDto save(UserDto userDto) throws Exception;
    UserDto findOne(Long id) throws Exception;
    UserDto update(UserDto userDto) throws Exception;
    PasswordResetToken createPasswordResetTokenForUser(User user, String token);

}
