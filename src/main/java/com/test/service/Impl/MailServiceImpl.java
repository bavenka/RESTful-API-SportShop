package com.test.service.Impl;

import com.test.model.entity.auth.PasswordResetToken;
import com.test.model.entity.auth.User;
import com.test.repository.UserRepository;
import com.test.service.MailService;
import com.test.service.PasswordResetTokenService;
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
    @Value("${server.name}")
    private String serverName;
    @Value("${server.port}")
    private int serverPort;
    @Value("${server.contextPath}")
    private String serverContextPath;

    @Override
    public void sendMessage(@NonNull Long userId,
                            @NonNull String email) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null
                || !user.getEmail().equals(email)) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_USER);
        }
        PasswordResetToken passwordResetPasswordResetToken = passwordResetTokenService.constructPasswordResetTokenForUser(user);
        if (passwordResetPasswordResetToken == null || passwordResetPasswordResetToken.getToken() == null) {
            throw new Exception(Constant.MESSAGE_NOT_CONSTRUCTED_TOKEN);
        }
        String textMessageToResetPassword = constructTextMessageToResetPassword(user, passwordResetPasswordResetToken.getToken());
        SimpleMailMessage simpleMailMessage = constructMailMessage(textMessageToResetPassword, email);
        if (simpleMailMessage == null) {
            throw new Exception(Constant.NOT_CONSTRUCTED_MESSAGE);
        }
        mailSender.send(simpleMailMessage);
    }

    private String constructLinkToResetPassword(@NonNull User user, @NonNull String token) {
        return "http://"
                + serverName
                + ":"
                + serverPort
                + serverContextPath
                + "/users"
                + "/changePassword?id="
                + user.getId()
                + "&token="
                + token;
    }

    private String constructTextMessageToResetPassword(@NonNull User user, @NonNull String token){
        return Constant.PASSWORD_RESET_GREETING
                + user.getUsername()
                + Constant.PASSWORD_RESET_TEXT
                + constructLinkToResetPassword(user,token)
                + Constant.PASSWORD_RESET_NOTE
                + Constant.PASSWORD_RESET_AUTHOR;
    }

    public SimpleMailMessage constructMailMessage(@NonNull String messageText,
                                                  @NonNull String email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setFrom(from);
        mailMessage.setSubject(Constant.PASSWORD_RESET_SUBJECT);
        mailMessage.setSentDate(new Date());
        mailMessage.setText(messageText);
        return mailMessage;
    }
}
