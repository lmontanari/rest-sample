package com.matera.repository;

import java.util.List;

import com.matera.exceptions.UserNotFoundException;
import com.matera.model.User;

/**
 * Database access to manage user document.
 * @author Lucas
 */
public interface UserRepository {

   /**
    * Saves a user.
    * @param user User to be saved.
    * @return User saved.
    */
   User save(User user);

   /**
    * Checks the existence of a user.
    * @param user User to be checked.
    * @return true if user exists on database.
    */
   boolean exists(User user);

   /**
    * Finds a User by it's login.
    * @param login Login of user to be found.
    * @return User
    * @throws UserNotFoundException If the user is not found on database.
    */
   User findByLogin(String login) throws UserNotFoundException;

   /**
    * Find all users on database.
    * @return List of users found.
    */
   List<User> findAll();

   /**
    * Delete user on database.
    * @param user User to be deleted.
    */
   void delete(User user);

}