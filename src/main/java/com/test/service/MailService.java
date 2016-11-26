package com.test.service;

import com.test.model.entity.auth.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Павел on 28.09.2016.
 */
@Service
public interface MailService {

    void sendMessage(Long userId, String email) throws Exception;

    SimpleMailMessage constructMailMessage(String messageText, String email);
}
