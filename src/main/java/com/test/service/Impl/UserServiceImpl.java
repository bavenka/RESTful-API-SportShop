package com.test.service.Impl;

import com.test.model.entity.auth.Role;
import com.test.model.entity.auth.User;
import com.test.model.dto.UserDto;
//import com.test.repository.PasswordResetTokenRepository;
import com.test.repository.RoleRepository;
import com.test.repository.UserRepository;
import com.test.service.UserService;
import com.test.utils.Constant;
import com.test.utils.Converter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Павел on 17.09.2016.
 */
@Component
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDto save(@NonNull UserDto userDto) throws Exception {

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
        userDto.setId(user.getId());
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
        User user = userRepository.findOne(userDto.getId());
        if (user == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_USER);
        }
        user.setPhone(userDto.getPhone());
        user.setName(userDto.getName());
        user.setAddress(Converter.toAddressEntity(userDto.getAddress()));
        userRepository.save(user);
        return userDto;
    }

    @Override
    public UserDto setRoleToUser(Long userId, Long roleId) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_USER);
        }
        Role role = roleRepository.findOne(roleId);
        if (role == null) {
            throw new Exception(Constant.MESSAGE_NOT_VALID_ROLE);
        }
        Set<Role> roles = user.getRoles();
        if(roles == null){
            roles = new HashSet<>();
        }
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
        return Converter.toUserDto(user);
    }

    @Override
    public UserDto deleteRoleFromUser(Long userId, Long roleId) throws Exception {
        Role deletedRole = null;
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_USER);
        }
        if (user.getRoles() == null) {
            throw new Exception("User has not a role!");
        }
        Role role = roleRepository.findOne(roleId);
        if (role == null) {
            throw new Exception(Constant.MESSAGE_NOT_VALID_ROLE);
        }
        Set<Role> roles = user.getRoles();
        for(Role userRole: roles){
            if(userRole.getId().equals(roleId)){
                deletedRole = userRole;
            }
        }
        if(deletedRole == null){
            throw new Exception("User has not this role!");
        }
        roles.remove(deletedRole);
        user.setRoles(roles);
        userRepository.save(user);
        return Converter.toUserDto(user);
    }

}
