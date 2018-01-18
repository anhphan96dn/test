package com.anh.service;

import com.anh.entity.UsersEntity;
import com.anh.exception.TokenInvalidException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    private static final String scretKey = "anhpro";
    private static final String expireDate = "3600";

    @Autowired
    private UserService userService;

    @Override
    public String getToken(UsersEntity usersEntity) {

        Calendar date = Calendar.getInstance();
        date.add(Calendar.SECOND, Integer.parseInt(expireDate));

        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject("verifyEmail")
                .setExpiration(date.getTime())
                .claim("user-id", usersEntity.getId())
                .signWith(SignatureAlgorithm.HS512, scretKey)
                .compact();
    }

    @Override
    public String verifyToken(String token) throws TokenInvalidException {
        Claims claims = Jwts.parser().setSigningKey(scretKey)
                .parseClaimsJws(token).getBody();
        Date dateExpire = claims.getExpiration();
        if(dateExpire.before(Calendar.getInstance().getTime()))
            throw new TokenInvalidException("Token is expired");

        String userId = claims.get("user-id").toString();
        UsersEntity usersEntity = userService.findById(Integer.parseInt(userId));

        if(usersEntity == null){
            throw new TokenInvalidException("Token is invalid");
        }
        usersEntity.setEnable(true);
        userService.saveCustomer(usersEntity);
        return userId;
    }
}
