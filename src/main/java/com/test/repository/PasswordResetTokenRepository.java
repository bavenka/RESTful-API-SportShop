package com.test.repository;

import com.test.model.entity.PasswordResetToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;




@Service
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Long> {
}
