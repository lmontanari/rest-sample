package com.matera.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.matera.exceptions.UserNotFoundException;
import com.matera.model.User;

/**
 * In-memory repository that simulates access to a database. It is meant to be used for tests only.
 * @author Lucas
 */
@Repository(value = "userRepository")
@Profile("test")
public class UserInMemoryRepository implements UserRepository {

   private Map<String, User> users;

   /**
    * Constructor.
    */
   public UserInMemoryRepository() {
      this.users = new HashMap<>();
   }

   /**
    * @see UserRepository#save(User)
    */
   @Override
   public User save(User user) {
      this.users.put(user.getLogin(), user);
      return user;
   }

   /**
    * @see UserRepository#delete(User)
    */
   @Override
   public void delete(User user) {
      this.users.remove(user.getLogin());
   }

   /**
    * @see com.matera.repository.UserRepository#findAll()
    */
   @Override
   public List<User> findAll() {
      Collection<User> values = this.users.values();
      List<User> result = new ArrayList<>(values);
      return result;
   }

   /**
    * @see UserRepository#findByLogin(String)
    */
   @Override
   public User findByLogin(String login) throws UserNotFoundException {
      User databaseUser = this.users.get(login);
      if (databaseUser == null) {
         throw new UserNotFoundException("User not found for login=" + login);
      }

      return databaseUser;
   }

   /**
    * @see UserRepository#exists(User)
    */
   @Override
   public boolean exists(User user) {
      return this.users.containsKey(user.getLogin());
   }

}