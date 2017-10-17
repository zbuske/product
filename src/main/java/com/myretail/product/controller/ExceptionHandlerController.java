package com.myretail.product.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    private static Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class.getName());

    // When the RedSky API is called, and there is no product matching the Product id,
    // it throws a HttpClientErrorException with a 404 status.
    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public void requestHandlingNoHandlerFound(HttpServletRequest request, HttpClientErrorException e) {
        logger.error(e.getMessage());
    }

    // If this was a production system, I'd fill out the exception handling more.
    // Just catching everything else for now and calling it a server error.
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public void handleError(HttpServletRequest request, Exception e) {
        logger.error(e.getMessage());
    }
}


