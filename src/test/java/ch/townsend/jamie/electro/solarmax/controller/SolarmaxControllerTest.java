package ch.townsend.jamie.electro.solarmax.controller;

import ch.townsend.jamie.electro.solarmax.SolarmaxTestDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.townsend.jamie.electro.solarmax.Application;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = { Application.class })
public class SolarmaxControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    final private static String host = SolarmaxTestDefaults.defaultHost;
    final private static int port = SolarmaxTestDefaults.defaultPort;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
//                .apply(springSecurity())
                .build();
    }

    @Test
    public void ping() throws Exception {

        final String urlString = "/ping?host=" + host + "&port=" + port;
        this.mockMvc.perform(get(urlString)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.status").value("ok"))
                ;
//        mvc.perform(post("/").with(csrf()));
//        mvc.perform(post("/").with(csrf().useInvalidToken()))

    }

}