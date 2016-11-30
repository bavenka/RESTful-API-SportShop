package com.test.service.Impl;

import com.test.model.dto.RoleDto;
import com.test.model.entity.auth.Role;
import com.test.model.entity.auth.RoleName;
import com.test.repository.RoleRepository;
import com.test.service.RoleService;
import com.test.utils.Constant;
import com.test.utils.Converter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Павел on 08.11.2016.
 */

@Component
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleDto editRole(@NonNull RoleDto roleDto) throws Exception {
        Role role = roleRepository.findOne(roleDto.getId());
        if (role == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_ROLE);
        }
        if (RoleName.valueOf(roleDto.getName().toUpperCase()) == null) {
            throw new Exception(Constant.MESSAGE_NOT_VALID_ROLE);
        }
        role.setName(roleDto.getName().toUpperCase());
        roleRepository.save(role);
        return roleDto;
    }

    @Override
    public RoleDto addRole(@NonNull RoleDto roleDto) throws Exception {
        Role role = Converter.toRole(roleDto);
        if (RoleName.valueOf(role.getName().toUpperCase()) == null) {
            throw new Exception(Constant.MESSAGE_NOT_VALID_ROLE);
        }
        roleRepository.save(role);
        roleDto.setId(role.getId());
        return roleDto;
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.delete(id);
    }
}
