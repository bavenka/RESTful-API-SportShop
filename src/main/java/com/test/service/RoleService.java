package com.test.service;

import com.test.model.dto.RoleDto;
import org.springframework.stereotype.Service;

/**
 * Created by Павел on 08.11.2016.
 */
@Service
public interface RoleService {
    RoleDto editRole(RoleDto roleDTO) throws Exception;
    RoleDto addRole(RoleDto roleDTO) throws Exception;
    void deleteRole(Long id);
}
