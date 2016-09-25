package com.test.utils;

import com.test.model.dto.UserDto;
import com.test.model.entity.DeviceType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Павел on 18.09.2016.
 */
@Component
public class JwtUtils {
    @Value("${token.secret}")
    private String secret;
    @Value("${token.expiration}")
    private Long expiration;

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.get("username", String.class);
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public String getDeviceFromToken(String token) {
        String device;
        try {
            final Claims claims = getClaimsFromToken(token);
            device = claims.get("device", String.class);
        } catch (Exception e) {
            device = null;
        }
        return device;
    }

    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Boolean ignoreTokenExpiration(String token) {
        String device = getDeviceFromToken(token);
        return (DeviceType.MOBILE.toString().equals(device)
                || DeviceType.TABLET.toString().equals(device));
    }

    private String generateDevice(Device device) {
        String deviceType = DeviceType.UNKNOWN.name();
        if(device.isNormal()){
            deviceType = DeviceType.NORMAL.name();
        }
        else if (device.isMobile()){
            deviceType = DeviceType.MOBILE.name();
        }
        else if(device.isTablet()){
            deviceType = DeviceType.TABLET.name();
        }
        return deviceType;
    }

    public String generateToken(UserDetails userDetails, Device device) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userDetails.getUsername());
        claims.put("device", generateDevice(device));
        return generateClaims(claims);
    }

    public String generateClaims(Map<String, Object> claims) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setClaims(claims);
        jwtBuilder.setIssuedAt(generateCurrentDate());
        jwtBuilder.setExpiration(generateExpirationDate());
        jwtBuilder.signWith(SignatureAlgorithm.HS512, secret);
        return jwtBuilder.compact();
    }

    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
                username.equals(userDetails.getUsername())
                        && (!isTokenExpired(token) || ignoreTokenExpiration(token))

                        );
    }
}
