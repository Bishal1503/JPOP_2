package com.JPoP2.controller;

import com.JPoP2.entity.User;
import com.JPoP2.entity.enums.Role;
import com.JPoP2.repository.UserRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ComponentScan("com.JpoP2")
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    private static User userObj;
    private static JSONObject userJsonObj;

    @BeforeClass
    public static void initialize() throws JSONException {
        userObj = new User();
        userObj.setId(1L);
        userObj.setFirstName("John");
        userObj.setLastName("Smith");
        userObj.setEmailId("john.smith@outlook.com");
        userObj.setRole(Role.ADMIN);

        userJsonObj = new JSONObject();
        userJsonObj.accumulate("id", 1);
        userJsonObj.accumulate("firstName", "John");
        userJsonObj.accumulate("lastName", "Smith");
        userJsonObj.accumulate("emailId", "john.smith@outlook.com");
        userJsonObj.accumulate("role", "ADMIN");
    }

    @Test
    public void testAddUser() throws Exception {
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(userObj);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/user").content(userJsonObj.toString())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isCreated()).andReturn();

        JSONAssert.assertEquals(userJsonObj.toString(), result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> usersList = new ArrayList<>();
        usersList.add(userObj);

        JSONArray usersJson = new JSONArray();
        usersJson.put(userJsonObj);

        Mockito.when(userRepository.findAll()).thenReturn(usersList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

        JSONAssert.assertEquals(usersJson.toString(), result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testGetUserFound() throws Exception {
        Optional<User> user = Optional.of(userObj);

        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(user);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/1").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

        JSONAssert.assertEquals(userJsonObj.toString(), result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testGetUserNotFound() throws Exception {
        Optional<User> user = Optional.empty();

        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(user);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/2").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isNotFound()).andReturn();

        assertEquals("", result.getResponse().getContentAsString());
    }

    @Test
    public void testDeleteUserByIdFound() throws Exception {
        Mockito.when(userRepository.existsById(Mockito.any())).thenReturn(true);
        Mockito.doNothing().when(userRepository).deleteById(Mockito.any());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/user/1").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

        assertEquals("1", result.getResponse().getContentAsString());
    }

    @Test
    public void testDeleteUserByIdNotFound() throws Exception {
        Mockito.when(userRepository.existsById(Mockito.any())).thenReturn(false);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/user/2").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isNotFound()).andReturn();

        assertEquals("", result.getResponse().getContentAsString());
    }

    @Test
    public void testUpdateUserByIdFound() throws Exception {
        Mockito.when(userRepository.existsById(Mockito.any())).thenReturn(true);
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(userObj);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/user/1").content(userJsonObj.toString())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

        JSONAssert.assertEquals(userJsonObj.toString(), result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testUpdateUserByIdNotFound() throws Exception {
        Mockito.when(userRepository.existsById(Mockito.any())).thenReturn(false);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/user/2").content(userJsonObj.toString())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isNotFound()).andReturn();

        assertEquals("", result.getResponse().getContentAsString());
    }
}
