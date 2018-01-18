package com.anh.controller;

import com.anh.exception.ConfirmException;
import com.anh.exception.ResponeErrorMessage;
import com.anh.exception.TokenInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionController {

    ResponeErrorMessage message;

    public ExceptionController(){
        message = new ResponeErrorMessage();
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ModelAndView handleConstraintViolationException(ConstraintViolationException exception){
        message.setCode(HttpStatus.BAD_REQUEST);
        message.setMessage(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("message", "your information is invalid! Please try again");
        return modelAndView;
    }

    @ExceptionHandler(ConfirmException.class)
    public ModelAndView handleConfirmException(){
        System.out.println("fail");
        message.setCode(HttpStatus.BAD_REQUEST);
        message.setMessage("your confirm code is wrong, try again!");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("message", message);
        return modelAndView;
    }

}
