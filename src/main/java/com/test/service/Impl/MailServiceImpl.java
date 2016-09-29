package com.test.service.Impl;

import com.test.model.entity.PasswordResetToken;
import com.test.model.entity.User;
import com.test.repository.UserRepository;
import com.test.service.MailService;
import com.test.service.UserService;
import com.test.utils.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by Павел on 28.09.2016.
 */
@Component
@Transactional
public class MailServiceImpl implements MailService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public SimpleMailMessage sendTokenToEmail(Long userId, String email) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception("User is not found!");
        }
        if (!user.getEmail().equals(email)) {
            throw new Exception("Email is not correct!");
        }
        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = userService.createPasswordResetTokenForUser(user, token);
        if (passwordResetToken == null) {
            throw new Exception("Token is not created!");
        }
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setFrom(ConstantUtils.LOGIN_EMAIL);
        mailMessage.setSubject("Reset password");
        mailMessage.setText(passwordResetToken.getToken());
        mailSender.send(mailMessage);
        return mailMessage;
    }

}
