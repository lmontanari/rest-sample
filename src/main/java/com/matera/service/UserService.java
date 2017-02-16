package com.matera.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.matera.exceptions.UserNotFoundException;
import com.matera.model.User;

public class UserService {

   private Map<String, User> users;

   public UserService() {
      this.users = new HashMap<>();
   }

   public void save(User user) {
      this.users.put(user.getLogin(), user);
   }

   public void updateByLogin(String login, User user) throws UserNotFoundException {
      User databaseUser = findUser(login);
      BeanUtils.copyProperties(user, databaseUser);

      save(databaseUser);
   }

   public void delete(User user) {
      this.users.remove(user);
   }

   public List<User> findAllUsers() {
      Collection<User> values = this.users.values();
      List<User> result= new ArrayList<>(values);
      return result;
   }

   public User findUserByLogin(String login) throws UserNotFoundException {
      return findUser(login);
   }

   private User findUser(String login) throws UserNotFoundException {
      User databaseUser = this.users.get(login);
      if (databaseUser == null) {
         throw new UserNotFoundException("User not found for login=" + login);
      }

      return databaseUser;
   }
}
