package com.matera.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.matera.exceptions.UserNotFoundException;
import com.matera.model.User;

@Repository
public class UserMongoDBRepository implements UserRepository {

   /**
    * Manage the operations on MongoDB.
    */
   private MongoOperations mongoOperations;

   /**
    * Constructor.
    * @param mongoOperations Manage the operations on MongoDB.
    */
   @Autowired
   public UserMongoDBRepository(MongoOperations mongoOperations) {
      this.mongoOperations = mongoOperations;
   }

   /**
    * @see UserRepository#save(User)
    */
   @Override
   public User save(User user) {
      mongoOperations.save(user);
      return user;
   }

   /**
    * @see UserRepository#delete(User)
    */
   @Override
   public void delete(User user) {
      Query query = queryByLogin(user.getLogin());
      mongoOperations.findAndRemove(query, User.class);
   }

   /**
    * @see UserRepository#exists(User)
    */
   @Override
   public boolean exists(User user) {
      Query query = queryByLogin(user.getLogin());
      User dbUser = mongoOperations.findOne(query, User.class);
      return dbUser != null;
   }

   /**
    * @see UserRepository#findByLogin(String)
    */
   @Override
   public User findByLogin(String login) throws UserNotFoundException {
      Query query = queryByLogin(login);
      User dbUser = mongoOperations.findOne(query, User.class);

      if (dbUser != null) {
         return dbUser;
      }

      throw new UserNotFoundException("User not found by login=" + login);
   }

   private Query queryByLogin(String login) {
      Query query = new Query(Criteria.where("login").is(login));
      return query;
   }

   /**
    * @see com.matera.repository.UserRepository#findAll()
    */
   @Override
   public List<User> findAll() {
      List<User> dbUsers = mongoOperations.findAll(User.class);

      return dbUsers;
   }
}