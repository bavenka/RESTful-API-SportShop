package com.test.rest.controller;

import com.test.model.dto.UserDto;
import com.test.service.AuthService;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Павел on 19.09.2016.
 */
@RestController
@RequestMapping("${route.authentication}")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) throws Exception {
        UserDto existingUserDto;
        try {
            existingUserDto = userService.save(userDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingUserDto, HttpStatus.CREATED);
    }

    @RequestMapping(value = "signin", method = RequestMethod.POST)
    public ResponseEntity<?> authenticationRequest(@RequestParam String username,
                                                   @RequestParam String password,
                                                   Device device) {
        String token = authService.createToken(username, password, device);
        if (token == null) {
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
