package com.karma.api.controller;

import com.karma.controller.ExceptionHandlerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice(basePackages = "com.trungtamjava.api")
public class APIExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler({Exception.class, IOException.class})
    public String ex(Exception ex) {
        logger.error("Error " + ex);
        return "error";
    }
}
