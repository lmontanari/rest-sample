package com.matera.exceptions;

/**
 * To be thrown when inserting a user that already exists on database.
 * @author Lucas Montanari
 */
public class UserAlreadyExists extends Exception {

   /**
    * serialVersionUID.
    */
   private static final long serialVersionUID = -4225619546502532831L;

   public UserAlreadyExists() {
   }

   public UserAlreadyExists(String message) {
      super(message);
   }

   public UserAlreadyExists(Exception cause) {
      super(cause);
   }

   public UserAlreadyExists(String message, Exception cause) {
      super(message, cause);
   }

   public UserAlreadyExists(String message, Exception cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }

}
