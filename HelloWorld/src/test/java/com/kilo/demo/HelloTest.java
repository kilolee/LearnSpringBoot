package com.kilo.demo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by kilo on 2018/4/25.
 */
@SpringBootTest
public class HelloTest {
    private MockMvc mockMvc;
@Before
    public void setUp(){
mockMvc = MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();
    }

    @Test
    public void getHello() throws Exception {
mockMvc.perform(MockMvcRequestBuilders.post("/hello?name=spring").accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print());
    }
}
