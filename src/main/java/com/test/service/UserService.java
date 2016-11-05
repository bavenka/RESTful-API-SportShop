package com.test.service;

import com.test.model.dto.UserDto;
import org.springframework.stereotype.Service;

/**
 * Created by Павел on 17.09.2016.
 */
@Service
public interface UserService {
    UserDto save(UserDto userDto) throws Exception;
    UserDto findOne(Long id) throws Exception;
    UserDto update(UserDto userDto) throws Exception;
}
