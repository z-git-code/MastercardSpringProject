package com.demo.cityconnection.configuration_test;

import com.demo.cityconnection.configuration.DataInitializer;
import com.demo.cityconnection.model.City;
import com.demo.cityconnection.repository.CityPairRepository;
import com.demo.cityconnection.repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ConfigurationTest {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityPairRepository cityPairRepository;

    @Autowired
    private DataInitializer dataInitializer;

    @Test
    public void testInit() {

        assertEquals(6, cityRepository.count());
        assertEquals(4, cityPairRepository.count());


    }
}
