package com.test.service;

import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Service;


/**
 * Created by Павел on 17.09.2016.
 */
@Service
public interface AuthService {
    String createToken(String username, String password, Device device);
}
