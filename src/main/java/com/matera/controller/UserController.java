package com.matera.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.matera.exceptions.UserNotFoundException;
import com.matera.model.User;
import com.matera.service.UserService;

@RestController
@RequestMapping(value = "/v1/users", produces = { APPLICATION_JSON_UTF8_VALUE })
public class UserController {

   private UserService userService;

   public UserController() {
      this.userService = new UserService();
   }

   @RequestMapping(method = RequestMethod.POST)
   public ResponseEntity<Void> createUser(@Valid @RequestBody User user, BindingResult bindingResult) {

      validateUserInput(bindingResult);

      userService.save(user);
      return ResponseEntity.ok().build();
   }

   @RequestMapping(value = "/{login}", method = RequestMethod.PUT)
   public void updateUserByLogin(@PathVariable String login, @Valid @RequestBody User user, BindingResult bindingResult) throws UserNotFoundException {
      validateUserInput(bindingResult);
      userService.updateByLogin(login, user);
   }

   @RequestMapping(value = "/{login}", method = RequestMethod.GET)
   public User findUserByLogin(@PathVariable String login) throws UserNotFoundException {
      return userService.findUserByLogin(login);
   }

   @RequestMapping(method = RequestMethod.GET)
   public List<User> findAllUsers() {
      return userService.findAllUsers();
   }

   private void validateUserInput(BindingResult bindingResult) {
      if (bindingResult != null && bindingResult.hasErrors()) {
         StringBuffer bff = new StringBuffer();
         // ObjectNode message = new ObjectNode(JsonNodeFactory.instance);
         for (FieldError error : bindingResult.getFieldErrors()) {
            bff.append("Field: ").append(error.getField());
            bff.append("Message: ").append(error.getDefaultMessage());
         }

         throw new IllegalArgumentException(bff.toString());
         // return ResponseEntity.badRequest().build();
      }
   }
}
