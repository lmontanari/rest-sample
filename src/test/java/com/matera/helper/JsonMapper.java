package com.matera.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json mapper used to create model classes to json.
 * @author Lucas
 */
public final class JsonMapper {

   /**
    * Transform Object into json string. 
    * @param obj Object to be transformed.
    * @return Json.
    */
   public static String asJsonString(Object o) {
      try {
         return new ObjectMapper().writeValueAsString(o);
      } catch (JsonProcessingException e) {
         throw new RuntimeException(e);
      }
   }
}
