package com.rkyadav.bigthinx;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rkyadav.bigthinx.model.User;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BigthinxApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void addUser() throws JsonProcessingException, Exception {
        String uri = "/api/user";
        User user = new User();
        user.setName("testUser");
        user.setDateOfBirth(new Date());
        user.setDescription("This is ramakant");
        user.setAddress("MS Villa");

        String inputJson = mapToJson(user);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "User Created Successfully");
    }
    
    @Test
    public void getUsers() throws JsonProcessingException, Exception {
        String uri = "/api/users";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
    
    @Test
    public void updateUser() throws JsonProcessingException, Exception {
        String uri = "/api/user";
        User user = new User();
        user.setName("testUser");
        user.setDateOfBirth(new Date());
        user.setDescription("This is ramakant");
        user.setAddress("MS Villa");

        String inputJson = mapToJson(user);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "User Updated Successfully");
    }
    
    @Test
    public void getUser() throws Exception {
        String uri = "/api/user/testUser";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void deleteUser() throws Exception {
        String uri = "/api/user/testUser";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

}
