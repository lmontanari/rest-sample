package com.matera.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model class for User.
 * @author Lucas
 */
@Document
public class User {

   /**
    * User's login. (primary key)
    */
   @NotBlank
   private String login;

   /**
    * User's email.
    */
   @NotBlank
   private String email;

   /**
    * User's name.
    */
   @NotBlank
   private String name;

   /**
    * User's surname.
    */
   @NotBlank
   private String surname;

   /**
    * User's phone (optional).
    */
   private String phone;

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

   public String getName() {
      return name;
   }

   public String getPhone() {
      return phone;
   }

   public String getSurname() {
      return surname;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public void setSurname(String surname) {
      this.surname = surname;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((email == null) ? 0 : email.hashCode());
      result = prime * result + ((login == null) ? 0 : login.hashCode());
      result = prime * result + ((name == null) ? 0 : name.hashCode());
      result = prime * result + ((phone == null) ? 0 : phone.hashCode());
      result = prime * result + ((surname == null) ? 0 : surname.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      User other = (User) obj;
      if (email == null) {
         if (other.email != null)
            return false;
      } else if (!email.equals(other.email))
         return false;
      if (login == null) {
         if (other.login != null)
            return false;
      } else if (!login.equals(other.login))
         return false;
      if (name == null) {
         if (other.name != null)
            return false;
      } else if (!name.equals(other.name))
         return false;
      if (phone == null) {
         if (other.phone != null)
            return false;
      } else if (!phone.equals(other.phone))
         return false;
      if (surname == null) {
         if (other.surname != null)
            return false;
      } else if (!surname.equals(other.surname))
         return false;
      return true;
   }

   @Override
   public String toString() {
      return "User [login=" + login + ", email=" + email + ", name=" + name + ", surname=" + surname + ", phone=" + phone + "]";
   }

}
