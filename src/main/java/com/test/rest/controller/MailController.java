package com.test.rest.controller;

import com.test.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Павел on 29.09.2016.
 */
@RestController
@RequestMapping("${route.users}")
public class MailController {
    @Autowired
    private MailService mailService;
    @RequestMapping(value = "{userId}/resetPassword", method = RequestMethod.POST)
    public ResponseEntity<?> sendToken(@PathVariable("userId") Long userId,@RequestParam String email) throws Exception {
        SimpleMailMessage mailMessage;
        try {
            mailMessage = mailService.sendTokenToEmail(userId, email);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(mailMessage, HttpStatus.OK);
    }
}
