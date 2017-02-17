package com.matera.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.matera.exceptions.UserAlreadyExists;
import com.matera.exceptions.UserNotFoundException;
import com.matera.model.User;
import com.matera.service.UserService;

/**
 * Rest API for creating, updating and finding users.
 * @author Lucas
 */
@RestController
@RequestMapping(value = "/v1/users", produces = { APPLICATION_JSON_UTF8_VALUE })
public class UserController {

   /**
    * Service used to manage users.
    */
   private UserService userService;

   /**
    * Constructor.
    * 
    * @param userService Service used to manage users.
    */
   @Autowired
   public UserController(UserService userService) {
      this.userService = userService;
   }

   /**
    * Creates new users.
    * @param user User to be created.
    * @param bindingResult Centralize validation information.
    * @return No content.
    * @throws UserAlreadyExists If the Login already exists on database.
    */
   @RequestMapping(method = RequestMethod.POST)
   public ResponseEntity<Void> createUser(@Valid @RequestBody User user, BindingResult bindingResult) throws UserAlreadyExists {

      validateUserInput(bindingResult);

      userService.createNewUser(user);
      return ResponseEntity.ok().build();
   }

   /**
    * Updates user information based on login in path parameter.
    * @param user User with information to update.
    * @param bindingResult Centralize validation information.
    * @return No content.
    * @throws UserNotFoundException If the Login doesn't exist.
    */
   @RequestMapping(value = "/{login}", method = RequestMethod.PUT)
   public void updateUserByLogin(@PathVariable String login, @Valid @RequestBody User user, BindingResult bindingResult) throws UserNotFoundException {
      validateUserInput(bindingResult);
      userService.updateByLogin(login, user);
   }

   /**
    * Updates user information based on login in path parameter.
    * @param user User with information to update.
    * @param bindingResult Centralize validation information.
    * @return No content.
    * @throws UserNotFoundException If the Login doesn't exist on database.
    */
   @RequestMapping(value = "/{login}", method = RequestMethod.GET)
   public User findUserByLogin(@PathVariable String login) throws UserNotFoundException {
      return userService.findUserByLogin(login);
   }

   /**
    * Find all users on database.
    * @return List of users found.
    */
   @RequestMapping(method = RequestMethod.GET)
   public List<User> findAllUsers() {
      return userService.findAll();
   }

   /**
    * Internal method to handle validation errors.
    * @param bindingResult
    */
   private void validateUserInput(BindingResult bindingResult) {
      if (bindingResult != null && bindingResult.hasErrors()) {
         StringBuffer bff = new StringBuffer();
         for (FieldError error : bindingResult.getFieldErrors()) {
            bff.append("Field: ").append(error.getField());
            bff.append("Message: ").append(error.getDefaultMessage());
         }

         throw new IllegalArgumentException(bff.toString());
      }
   }
}
