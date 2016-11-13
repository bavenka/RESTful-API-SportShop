package com.test.service.Impl;

import com.test.model.entity.auth.PasswordResetToken;
import com.test.model.entity.auth.User;
import com.test.repository.PasswordResetTokenRepository;
import com.test.repository.UserRepository;
import com.test.service.PasswordResetTokenService;
import com.test.utils.Constant;
import lombok.NonNull;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Павел on 04.10.2016.
 */
@Component
@Transactional
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    @Autowired
    private UserRepository userRepository;
    @Value("${token.expiration}")
    private Long tokenExpiration;

    @Override
    public PasswordResetToken constructPasswordResetTokenForUser(@NonNull User user) {
        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = user.getPasswordResetToken();
        if (passwordResetToken != null) {
            passwordResetToken.setExpiration(DateUtils.addMinutes(new Date(), Constant.AMOUNT_MINUTES_VALID_TOKEN));
            passwordResetToken.setToken(token);
            return passwordResetToken;
        }
        passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(token);
        passwordResetToken.setExpiration(DateUtils.addMinutes(new Date(), Constant.AMOUNT_MINUTES_VALID_TOKEN));
        passwordResetToken.setUser(user);
        return passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    public void isPasswordResetTokenValid(@NonNull Long userId,
                                          @NonNull String token) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null || user.getPasswordResetToken() == null
                || !StringUtils.hasText(token)) {

            throw new Exception(Constant.MESSAGE_INVALID_DATA);
        }
        PasswordResetToken passwordResetToken = user.getPasswordResetToken();
        if (!passwordResetToken.getToken().equals(token)
                || passwordResetToken.getExpiration().before(new Date())) {
            passwordResetTokenRepository.delete(passwordResetToken.getId());
            throw new Exception(Constant.MESSAGE_NOT_VALID_TOKEN);
        }
    }

    @Override
    public void saveNewPassword(String newPassword) throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_USER);
        }
        User user = userRepository.findByUsername(userDetails.getUsername());
        if (user == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_USER);
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
