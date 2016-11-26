package com.test.rest.controller;

import com.test.model.dto.UserDto;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * Created by Павел on 17.09.2016.
 */
@RestController
@RequestMapping("${route.users}")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) throws Exception {
        UserDto userDto = userService.findOne(id);
        if (userDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDto, HttpStatus.FOUND);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity<?> getUser(@RequestBody UserDto userDto,
                                     @RequestHeader(name = "Authorization") String token) throws Exception {
        UserDto existingUserDto;
        try {
            existingUserDto = userService.update(userDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingUserDto, HttpStatus.OK);
    }

    @RequestMapping(value = "{userId}/add/role/{roleId}", method = RequestMethod.PUT)
    public ResponseEntity<?> setRoleToUser(@PathVariable("userId") Long userId,
                                           @PathVariable("roleId") Long roleId,
                                           @RequestHeader(name = "Authorization") String token) throws Exception {
        UserDto existingUserDto;
        try {
            existingUserDto = userService.setRoleToUser(userId, roleId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingUserDto, HttpStatus.OK);
    }

    @RequestMapping(value = "{userId}/delete/role/{roleId}", method = RequestMethod.PUT)
    public ResponseEntity<?> deleteRoleFromUser(@PathVariable("userId") Long userId,
                                                @PathVariable("roleId") Long roleId,
                                                @RequestHeader(name = "Authorization") String token) throws Exception {
        UserDto existingUserDto;
        try {
            existingUserDto = userService.deleteRoleFromUser(userId, roleId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingUserDto, HttpStatus.OK);
    }
}
