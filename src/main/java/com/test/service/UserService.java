package com.test.service;

import com.test.model.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Павел on 17.09.2016.
 */
@Service
public interface UserService {
    UserDto addUser(UserDto userDto) throws Exception;
    UserDto getUser(Long id) throws Exception;
}
