package com.matera.exceptions;

public class UserNotFoundException extends Exception {

   /**
    * serialVersionUID.
    */
   private static final long serialVersionUID = 3484045795608547556L;

   public UserNotFoundException() {
      super();
   }

   public UserNotFoundException(String message, Exception cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }

   public UserNotFoundException(String message, Exception cause) {
      super(message, cause);
   }

   public UserNotFoundException(String message) {
      super(message);
   }

   public UserNotFoundException(Exception cause) {
      super(cause);
   }

}
