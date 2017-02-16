package com.matera.controller;

import static com.matera.helper.JsonMapper.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.matera.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTest {

   @Autowired
   private WebApplicationContext context;

   private MockMvc mvc;

   @Before
   public void setUp() {
      this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
   }

   @Test
   public void testGetAllUsers200() throws Exception {
      this.mvc.perform(get("/v1/users")).andExpect(status().isOk());
   }

   @Test
   public void testCreatingAndGettingUser() throws Exception {
      String email = "lucas_montanari at hotmail dot com";
      User user = new User();
      user.setEmail(email);
      user.setId(1l);
      user.setLogin("lmi");

      this.mvc.perform(
            post("/v1/users").contentType(MediaType.APPLICATION_JSON_UTF8)
                  .content(asJsonString(user)))
            .andExpect(status().isOk());

      this.mvc.perform(get("/v1/users/lmi")).andExpect(status().isOk()).andExpect(jsonPath("email", equalTo(email)));
   }

   // @Test
   // public void findByNameAndCountry() throws Exception {
   //
   // this.mvc.perform(
   // get("/api/cities/search/findByNameAndCountryAllIgnoringCase?name=Melbourne&country=Australia"))
   // .andExpect(status().isOk())
   // .andExpect(jsonPath("state", equalTo("Victoria")))
   // .andExpect(jsonPath("name", equalTo("Melbourne")));
   // }
   //
   // @Test
   // public void findByContaining() throws Exception {
   //
   // this.mvc.perform(
   // get("/api/cities/search/findByNameContainingAndCountryContainingAllIgnoringCase?name=&country=UK"))
   // .andExpect(status().isOk())
   // .andExpect(jsonPath("_embedded.cities", hasSize(3)));
   // }
}
