package com.hwork.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    Logger logger = LoggerFactory.getLogger(UserControllerTest.class);
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void userQueryTest() {
        try {
            String result=mockMvc.perform(MockMvcRequestBuilders.get("/user").param("username","zhangguorong").contentType(MediaType.APPLICATION_JSON_UTF8)).
                    andExpect(MockMvcResultMatchers.status().isOk()).
                    andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
            .andReturn().getResponse().getContentAsString();
            logger.info("result:"+result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createUserTest() {
        Date birthDay = new Date();
        logger.info("birthday : " + birthDay.getTime());
        String userInfo = "{\"userName\":\"tom\",\"password\":null,\"birthDay\":"+birthDay.getTime()+"}";
        String result="";
        try {
            result=mockMvc.perform(MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON_UTF8).content(userInfo))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(3))
                    .andReturn().getResponse().getContentAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("createUser result :"+result);
    }

    @Test
    public void updateUserTest() {
        Date birthDay = new Date();
        logger.info("birthday : " + birthDay.getTime());
        String userInfo = "{\"userId\":\"1\",\"userName\":\"zhourunfa\",\"password\":null,\"birthDay\":"+birthDay.getTime()+"}";
        String result="";
        try {
            result=mockMvc.perform(MockMvcRequestBuilders.put("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8).content(userInfo))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1))
                    .andReturn().getResponse().getContentAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("createUser result :"+result);
    }


    @Test
    public void getUseDetailTest() {
        try {
            String resultDetail=mockMvc.perform(MockMvcRequestBuilders.get("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("tom"))
                    .andReturn().getResponse().getContentAsString();
            logger.info("resultDetail:"+resultDetail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
