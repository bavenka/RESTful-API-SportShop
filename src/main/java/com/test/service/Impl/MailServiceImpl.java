package com.test.service.Impl;

import com.test.model.entity.PasswordResetToken;
import com.test.model.entity.User;
import com.test.repository.UserRepository;
import com.test.service.MailService;
import com.test.service.UserService;
import com.test.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
    @Value("${mail.from}")
    private String from;

    @Override
    public SimpleMailMessage sendMessage(HttpServletRequest request, Long userId, String email) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception("User is not found!");
        }
        if (!user.getEmail().equals(email)) {
            throw new Exception("Email is not correct!");
        }
        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetPasswordResetToken = userService.createPasswordResetTokenForUser(user, token);
        if (passwordResetPasswordResetToken == null) {
            throw new Exception("PasswordResetToken is not created!");
        }
        String appUrl = "http://"
                + request.getServerName()
                + ":"
                + request.getServerPort()
                + request.getContextPath()
                + "/users/{"
                + user.getId()
                + "}/"
                + "changePassword?token="
                + token;
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setFrom(from);
        mailMessage.setSubject(Constant.PASSWORD_RESET_SUBJECT);
        mailMessage.setSentDate(new Date());
        mailMessage.setText(Constant.PASSWORD_RESET_GREETING
                + user.getUsername()
                + Constant.PASSWORD_RESET_TEXT
                + appUrl
                + Constant.PASSWORD_RESET_NOTE
                + Constant.PASSWORD_RESET_AUTHOR
        );
        mailSender.send(mailMessage);
        return mailMessage;
    }

}
