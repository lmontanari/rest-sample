package com.matera.controller;

import static com.matera.helper.JsonMapper.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

   private MockMvc mvc;

   @Autowired
   private WebApplicationContext context;

   public MockMvc getMvc() {
      return mvc;
   }

   @Before
   public void setUp() {
      this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
   }

   @Test
   public void testGetAllUsers200() throws Exception {
      getMvc().perform(get("/v1/users")).andExpect(status().isOk());
   }

   @Test
   public void testCreatingSameUserTwice() throws Exception {
      String email = "lucas_montanari at hotmail dot com";
      User user = new User();
      user.setEmail(email);
      user.setLogin("lucasm");
      user.setName("Lucas");
      user.setSurname("Montanari");

      // First time.
      getMvc().perform(
            post("/v1/users").contentType(MediaType.APPLICATION_JSON_UTF8)
                  .content(asJsonString(user)))
            .andExpect(status().isOk());

      // Second time. (should get error)
      getMvc().perform(
            post("/v1/users").contentType(MediaType.APPLICATION_JSON_UTF8)
                  .content(asJsonString(user)))
            .andExpect(status().is(409));
   }

   @Test
   public void testCreatingAndGettingUser() throws Exception {
      String email = "lucas_montanari at hotmail dot com";
      String login = "lmi";
      String name = "Lucas";
      String surname = "Montanari";
      String phone = "5519999999999";

      User user = new User();
      user.setEmail(email);
      user.setLogin(login);
      user.setName(name);
      user.setSurname(surname);
      user.setPhone("5519999999999");

      getMvc().perform(
            post("/v1/users").contentType(MediaType.APPLICATION_JSON_UTF8)
                  .content(asJsonString(user)))
            .andExpect(status().isOk());

      getMvc().perform(get("/v1/users/lmi")).andExpect(status().isOk()).andExpect(jsonPath("email", equalTo(email))).andExpect(jsonPath("login", equalTo(login)))
            .andExpect(jsonPath("name", equalTo(name))).andExpect(jsonPath("surname", equalTo(surname))).andExpect(jsonPath("phone", equalTo(phone)));
   }

   @Test
   public void testCreatingAndUpdatingUser() throws Exception {
      String email = "lucas_montanari at hotmail dot com";
      User user = new User();
      user.setEmail(email);
      user.setLogin("lmi2");
      user.setName("Lucasxxxxxxxxxxxxxxx");
      user.setSurname("Montanarixxxxxxxxxxx");

      getMvc().perform(
            post("/v1/users").contentType(MediaType.APPLICATION_JSON_UTF8)
                  .content(asJsonString(user)))
            .andExpect(status().isOk());

      email = "other_email at hotmail dot com";
      String name = "Lucas";
      String surname = "Montanari";
      String phone = "5519999999999";

      user = new User();
      user.setEmail(email);
      user.setLogin("lmi3");
      user.setName(name);
      user.setSurname(surname);
      user.setPhone(phone);

      getMvc().perform(put("/v1/users/lmi2").contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(asJsonString(user))).andExpect(status().isOk());

      getMvc().perform(get("/v1/users/lmi3").contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(asJsonString(user))).andExpect(status().isOk())
            .andExpect(jsonPath("email", equalTo(email))).andExpect(jsonPath("login", equalTo("lmi3")))
            .andExpect(jsonPath("name", equalTo(name))).andExpect(jsonPath("surname", equalTo(surname))).andExpect(jsonPath("phone", equalTo(phone)));

      getMvc().perform(get("/v1/users/lmi2").contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().is(404));
   }

   @Test
   public void testCreatingUserWithABadRequest() throws Exception {
      String email = "lucas_montanari at hotmail dot com";
      User user = new User();
      user.setEmail(email);
      user.setLogin("lmi5");
      // user.setName("Lucas");
      user.setSurname("Montanari");

      getMvc().perform(
            post("/v1/users").contentType(MediaType.APPLICATION_JSON_UTF8)
                  .content(asJsonString(user)))
            .andExpect(status().isBadRequest());

      user.setName("notNull");
      user.setSurname(null);
      getMvc().perform(
            post("/v1/users").contentType(MediaType.APPLICATION_JSON_UTF8)
                  .content(asJsonString(user)))
            .andExpect(status().isBadRequest());

      user.setSurname("notNull");
      user.setEmail(null);
      getMvc().perform(
            post("/v1/users").contentType(MediaType.APPLICATION_JSON_UTF8)
                  .content(asJsonString(user)))
            .andExpect(status().isBadRequest());

      user.setEmail("notNull");
      user.setLogin(null);
      getMvc().perform(
            post("/v1/users").contentType(MediaType.APPLICATION_JSON_UTF8)
                  .content(asJsonString(user)))
            .andExpect(status().isBadRequest());

      user.setLogin("notNull");
      getMvc().perform(
            post("/v1/users").contentType(MediaType.APPLICATION_JSON_UTF8)
                  .content(asJsonString(user)))
            .andExpect(status().isOk());

      getMvc().perform(get("/v1/users/notNull").contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(asJsonString(user))).andExpect(status().isOk()).andExpect(jsonPath("email", equalTo("notNull"))).andExpect(jsonPath("login", equalTo("notNull")))
            .andExpect(jsonPath("name", equalTo("notNull"))).andExpect(jsonPath("surname", equalTo("notNull")));
   }

}
