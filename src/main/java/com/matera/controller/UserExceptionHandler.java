package com.matera.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.matera.exceptions.UserNotFoundException;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

   private final Logger logger = LoggerFactory.getLogger(this.getClass());

   @ExceptionHandler({ UserNotFoundException.class })
   public ResponseEntity<String> notFound(HttpServletRequest req, Exception e) {
      logger.debug("Resource not found: {}", e.getMessage());
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
   }

   @ExceptionHandler({ IllegalArgumentException.class })
   public ResponseEntity<String> badRequest(HttpServletRequest req, Exception e) {
      logger.debug("Illegal argument exception causing bad request: {}", e.getMessage());
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
   }
}
