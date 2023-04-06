package com.demo.cityconnection.controller_test;

import com.demo.cityconnection.controller.CityConnectionController;
import com.demo.cityconnection.service.CityConnectionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;

@WebMvcTest(CityConnectionController.class)
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityConnectionService cityService;

    @Test
    public void testAreCitiesConnected() throws Exception {
        String origin = "Boston";
        String destination = "New York";
        boolean connected = true;


        when(cityService.areCitiesConnected(origin, destination)).thenReturn(connected);


        mockMvc.perform(MockMvcRequestBuilders.get("/connected")
                        .param("origin", origin)
                        .param("destination", destination))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("yes"));


        verify(cityService, times(1)).areCitiesConnected(origin, destination);
    }
}
