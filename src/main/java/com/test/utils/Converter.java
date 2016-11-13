package com.test.utils;

import com.test.model.dto.AddressDto;
import com.test.model.dto.PasswordResetTokenDto;
import com.test.model.dto.RoleDto;
import com.test.model.entity.auth.Address;
import com.test.model.entity.auth.PasswordResetToken;
import com.test.model.entity.auth.Role;
import com.test.model.entity.auth.User;
import com.test.model.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Павел on 17.09.2016.
 */
public class Converter {
    public static User toUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setName(userDto.getName());
        user.setPhone(userDto.getPhone());
        AddressDto addressDto = userDto.getAddress();
        if (addressDto != null) {
            Address address = toAddressEntity(addressDto);
            user.setAddress(address);
        }
        if (userDto.getRoles() != null) {
            Set<GrantedAuthority> collection = userDto.getRoles().stream().collect(Collectors.toSet());
            Set<Role> roles = new HashSet<>();
            for (GrantedAuthority grantedAuthority : collection) {
                Role role = new Role();
                role.setName(grantedAuthority.getAuthority());
            }
            user.setRoles(roles);
        }
        return user;
    }

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setPhone(user.getPhone());
        userDto.setName(user.getName());
        Address address = user.getAddress();
        if (address != null) {
            AddressDto addressDto = toAddressDto(address);
            userDto.setAddress(addressDto);
        }
        if (user.getRoles() != null) {
            Set<Role> roles = user.getRoles();
            Set<GrantedAuthority> collection = new HashSet<>();
            for (Role role : roles) {
                collection.add(new SimpleGrantedAuthority(role.getName()));
            }
            userDto.setRoles(collection);
        }
        return userDto;
    }

    public static PasswordResetTokenDto toTokenDto(PasswordResetToken passwordResetToken) {
        PasswordResetTokenDto passwordResetTokenDto = new PasswordResetTokenDto();
        passwordResetTokenDto.setId(passwordResetToken.getId());
        passwordResetTokenDto.setToken(passwordResetToken.getToken());
        passwordResetTokenDto.setExpiration(passwordResetToken.getExpiration());
        return passwordResetTokenDto;
    }

    public static AddressDto toAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setStreet(address.getStreet());
        addressDto.setCity(address.getCity());
        addressDto.setZipcode(address.getZipcode());
        addressDto.setCountry(address.getCountry());
        return addressDto;
    }

    public static Address toAddressEntity(AddressDto addressDto) {
        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setZipcode(addressDto.getZipcode());
        address.setCountry(addressDto.getCountry());
        return address;
    }

    public static RoleDto toRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;
    }

    public static Role toRole(RoleDto roleDto) {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName().toUpperCase());
        return role;
    }
}
