package com.matera.model;

import org.hibernate.validator.constraints.NotBlank;

public class User {

   private Long id;
   
   @NotBlank
   private String login;
   
   @NotBlank
   private String email;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getLogin() {
      return login;
   }

   public void setLogin(String login) {
      this.login = login;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }
   
   
}
