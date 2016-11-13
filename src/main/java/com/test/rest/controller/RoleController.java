package com.test.rest.controller;

import com.test.model.dto.RoleDto;
import com.test.model.dto.UserDto;
import com.test.service.RoleService;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * Created by Павел on 08.11.2016.
 */
@RestController
@RequestMapping("${route.roles}")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity<?> editRole(@RequestBody RoleDto roleDto,
                                      @RequestHeader(name = "Authorization") String token) throws Exception {
        RoleDto existingRoleDto;
        try {
            existingRoleDto = roleService.editRole(roleDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingRoleDto, HttpStatus.OK);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<?> addRole(@RequestBody RoleDto roleDto,
                                     @RequestHeader(name = "Authorization") String token) throws Exception {
        RoleDto existingRoleDto;
        try {
            existingRoleDto = roleService.addRole(roleDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingRoleDto, HttpStatus.OK);
    }
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> addRole(@PathVariable Long id,
                                     @RequestHeader(name = "Authorization") String token) throws Exception {
        try {
            roleService.deleteRole(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }
}
