package com.karma.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice(basePackages = "com.trungtamjava")
public class ExceptionHandlerController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler({Exception.class, IOException.class})
    public String ex(Exception ex) {
        logger.error("Error " + ex);
        return "error";
    }
    @ExceptionHandler(NullPointerException.class)
    public String nullEx(Exception ex) {
        logger.error("Error " + ex);
        return "error";
    }
}
