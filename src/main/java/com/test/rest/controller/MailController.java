package com.test.rest.controller;

import com.test.model.dto.TokenDto;
import com.test.service.MailService;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Павел on 29.09.2016.
 */
@RestController
@RequestMapping("${route.users}")
public class MailController {
    @Autowired
    private MailService mailService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "{userId}/resetPassword", method = RequestMethod.POST)
    public ResponseEntity<?> sendMessage(HttpServletRequest request,
                                         @PathVariable("userId") Long userId,
                                         @RequestParam String email) throws Exception {
        SimpleMailMessage message;
        try {
            message = mailService.sendMessage(request ,userId, email);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "{userId}/changePassword", method = RequestMethod.GET)
    public ResponseEntity<?> receiveMessage(HttpServletRequest request,
                                         @PathVariable("userId") Long userId,
                                         @RequestParam String token) throws Exception {
        TokenDto tokenDto;
        try {
             tokenDto = userService.getToken(userId, token);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(tokenDto, HttpStatus.OK);
    }
}
