package com.matera.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

/**
 * Spring bean to configure a connection to MongoDB. 
 * MongoDB should be install on localhost and default port. 
 * The database name will be automatically created if not exists.
 * 
 * @author Lucas
 */
@Configuration
public class MongoConfig {

   /**
    * Mongo's database name.
    */
   private static final String DATABASE_NAME = "users";

   public MongoDbFactory mongoDbFactory() throws Exception {
      SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(new MongoClient(), DATABASE_NAME);

      return simpleMongoDbFactory;
   }

   @Bean
   public MongoTemplate mongoTemplate() throws Exception {
      return new MongoTemplate(mongoDbFactory());
   }
}
