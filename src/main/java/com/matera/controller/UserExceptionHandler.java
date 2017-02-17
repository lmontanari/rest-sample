package com.matera.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import com.matera.exceptions.UserAlreadyExists;
import com.matera.exceptions.UserNotFoundException;

/**
 * Centralized exception handling across all {@code @RequestMapping} methods through {@code UserController}.
 * @author Lucas
 */
@ControllerAdvice
public class UserExceptionHandler extends DefaultHandlerExceptionResolver {

   private final Logger logger = LoggerFactory.getLogger(this.getClass());

   @ExceptionHandler({ UserNotFoundException.class })
   public ResponseEntity<Void> notFound(HttpServletRequest req, Exception e) {
      logger.warn("Resource not found: {}", e.getMessage());
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

   @ExceptionHandler({ IllegalArgumentException.class })
   public ResponseEntity<Void> badRequest(HttpServletRequest req, Exception e) {
      logger.warn("Illegal argument exception causing bad request: {}", e.getMessage());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   }

   @ExceptionHandler({ UserAlreadyExists.class })
   public ResponseEntity<Void> conflict(HttpServletRequest req, Exception e) {
      logger.warn("User already exists: {}", e.getMessage());
      return new ResponseEntity<>(HttpStatus.CONFLICT);
   }

}
