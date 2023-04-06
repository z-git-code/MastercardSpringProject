package com.demo.cityconnection.service_test;


import com.demo.cityconnection.controller.CityConnectionController;
import com.demo.cityconnection.service.CityConnectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ServiceTest {

    private MockMvc mockMvc;

    @Autowired
    private CityConnectionController controller;

    @MockBean
    private CityConnectionService service;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testTwoCitiesAreConnected() throws Exception {

        String origin = "Boston";
        String destination = "New York";

        when(service.areCitiesConnected(origin, destination)).thenReturn(true);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/connected")
                        .param("origin", origin)
                        .param("destination", destination)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().string("yes"))
                        .andReturn();

        verify(service, times(1)).areCitiesConnected(origin, destination);



    }

}

