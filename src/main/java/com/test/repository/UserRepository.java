package com.test.repository;

import com.test.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


/**
 * Created by Павел on 17.09.2016.
 */
@Service
public interface UserRepository extends CrudRepository<User, Long> {

}
