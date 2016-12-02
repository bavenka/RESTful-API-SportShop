package com.test.repository;

import com.test.model.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Павел on 08.11.2016.
 */
@Service
public interface RoleRepository extends CrudRepository<Role, Long> {
}
