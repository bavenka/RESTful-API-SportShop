package com.test.service.Impl;

import com.test.model.entity.PasswordResetToken;
import com.test.model.entity.User;
import com.test.repository.UserRepository;
import com.test.service.MailService;
import com.test.service.PasswordResetTokenService;
import com.test.service.UserService;
import com.test.utils.Constant;
import lombok.NonNull;
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
    private PasswordResetTokenService passwordResetTokenService;
    @Autowired
    private JavaMailSender mailSender;
    @Value("${mail.from}")
    private String from;

    @Override
    public void sendMessage(HttpServletRequest request,
                            @NonNull Long userId,
                            @NonNull String email) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null
                || !user.getEmail().equals(email)) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_USER);
        }
        PasswordResetToken passwordResetPasswordResetToken = passwordResetTokenService.constructPasswordResetTokenForUser(user);
        if (passwordResetPasswordResetToken == null) {
            throw new Exception(Constant.MESSAGE_NOT_CONSTRUCTED_TOKEN);
        }
        SimpleMailMessage simpleMailMessage = constructMessage(request, user, passwordResetPasswordResetToken.getToken(), email);
        if (simpleMailMessage == null) {
            throw new Exception(Constant.NOT_CONSTRUCTED_MESSAGE);
        }
        mailSender.send(simpleMailMessage);
    }

    @Override
    public SimpleMailMessage constructMessage(HttpServletRequest request,
                                              @NonNull User user,
                                              @NonNull String token,
                                              @NonNull String email) {
        String appUrl = "http://"
                + request.getServerName()
                + ":"
                + request.getServerPort()
                + request.getContextPath()
                + "/users"
                + "/changePassword?id="
                + user.getId()
                + "&token="
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
        return mailMessage;
    }
}
