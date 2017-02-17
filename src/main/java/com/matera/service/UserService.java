package com.matera.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matera.exceptions.UserAlreadyExists;
import com.matera.exceptions.UserNotFoundException;
import com.matera.model.User;
import com.matera.repository.UserRepository;

/**
 * User service that encapsulates all business logic.
 * @author Lucas
 */
@Service
public class UserService {

   /**
    * Database access to user document.
    */
   private UserRepository userRepository;

   /**
    * Constructor.
    * @param userRepository Database access to user document.
    */
   @Autowired
   public UserService(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   /**
    * Creates a new user in database.
    * @param user User
    * @throws UserAlreadyExists If user already exists on database.
    */
   public void createNewUser(User user) throws UserAlreadyExists {
      if (userRepository.exists(user)) {
         throw new UserAlreadyExists("User already exists with login=" + user.getLogin());
      }
      userRepository.save(user);
   }

   /**
    * Updates user in database based on it's login.
    * @param login User's login.
    * @param user User.
    * @throws UserNotFoundException If user was not found by it's login on database.
    */
   public void updateByLogin(String login, User user) throws UserNotFoundException {
      User databaseUser = userRepository.findByLogin(login);
      BeanUtils.copyProperties(user, databaseUser);

      // Login changed?
      if (!login.equals(user.getLogin())) {
         // Remove old user.
         user.setLogin(login);
         userRepository.delete(user);
      }

      userRepository.save(databaseUser);
   }

   /**
    * Find all users on database.
    * @return List of users found.
    */
   public List<User> findAll() {
      return userRepository.findAll();
   }

   /**
    * Finds a User by it's login.
    * @param login Login of user to be found.
    * @return User User found.
    * @throws UserNotFoundException If the user is not found on database.
    */
   public User findUserByLogin(String login) throws UserNotFoundException {
      return userRepository.findByLogin(login);
   }

}
