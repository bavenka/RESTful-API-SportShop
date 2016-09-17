package com.test.controller;

import com.test.model.User;
import com.test.model.dto.UserDto;
import com.test.service.Impl.UserServiceImpl;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Павел on 17.09.2016.
 */
@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) throws Exception {
        return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.CREATED);
    }
}
