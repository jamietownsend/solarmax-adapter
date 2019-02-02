package com.servebeer.please.solarmax.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.servebeer.please.solarmax.Application;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = { Application.class })
public class SolarmaxControllerIT {

    final private static String HOST = SolarmaxTestDefaults.HOST;
    final private static int PORT = SolarmaxTestDefaults.PORT;
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
//                .apply(springSecurity())
                .build();
    }

    @Test
    public void ping() throws Exception {

        final String urlString = "/ping?host=" + HOST + "&port=" + PORT;
        this.mockMvc.perform(get(urlString)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
//                .andExpect(jsonPath("$.connectionStatus").value("ok"))
                .andExpect(jsonPath("$.connectionStatus").exists())
                ;
//        mvc.perform(post("/").with(csrf()));
//        mvc.perform(post("/").with(csrf().useInvalidToken()))

    }

}