package com.myretail.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    // When the RedSky API is called, and there is no product matching the Product id,
    // it throws a HttpClientErrorException with a 404 status.
    // Getting the status from the exception in case it's not a 404.
    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseBody
    public ModelAndView requestHandlingNoHandlerFound(HttpServletRequest request, HttpClientErrorException e) {
        ModelAndView mav = new ModelAndView();
        mav.setStatus(e.getStatusCode());

        return mav;
    }

    // If this was a production system, I'd fill out the exception handling more.
    // Just catching everything else for now and calling it a server error.
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public void handleError(HttpServletRequest request, Exception e) {
    }
}


