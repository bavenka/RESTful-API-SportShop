package com.test.repository;

import com.test.model.entity.PasswordResetToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Павел on 29.09.2016.
 */
@Service
public interface TokenRepository extends CrudRepository<PasswordResetToken, Long> {
}
