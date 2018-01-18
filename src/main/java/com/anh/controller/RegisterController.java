package com.anh.controller;


import com.anh.entity.UsersEntity;
import com.anh.model.RegisterParam;
import com.anh.exception.TokenInvalidException;
import com.anh.service.EmailService;
import com.anh.service.JwtTokenService;
import com.anh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @GetMapping("/register")
    public String register(){
        return "register";
    }


    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("user") @Valid RegisterParam registerParam) throws ConstraintViolationException {
        String username = registerParam.getUserName();
        String password = registerParam.getPassWord();
        UsersEntity user = new UsersEntity(username, encoder.encode(password));
        userService.saveCustomer(user);


        String jwtToken = jwtTokenService.getToken(user);
        String link = "http://localhost:8080/confirm?token="+jwtToken;
        emailService.sendSimpleMessage(registerParam.getUserName(), "confirm", "Please click this link to active your account: "+link);

        return "confirm";
    }

    @GetMapping("/confirm")
    public RedirectView confirm(@RequestParam String token) throws TokenInvalidException{
        jwtTokenService.verifyToken(token);
        return new RedirectView("/user-login");
    }
}
