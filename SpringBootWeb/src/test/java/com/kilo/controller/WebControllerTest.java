package com.kilo.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by kilo on 2018/4/25.
 */
public class WebControllerTest {

    private MockMvc mockMvc;
    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(new WebController()).build();
    }

//    @Test
    public void getUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/getUser").accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print());
    }

//    @Test
    public void getUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/getUsers").accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print());
    }

//    @Test
    public void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/get/yoyo").accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print());
    }

    @Test
    public void saveUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/saveUser")
                .param("name","")
                .param("age","666")
                .param("pass","123")).andDo(print());
    }
}