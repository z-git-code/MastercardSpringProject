package com.demo.cityconnection.configuration;

import com.demo.cityconnection.model.City;
import com.demo.cityconnection.model.CityPair;
import com.demo.cityconnection.repository.CityPairRepository;
import com.demo.cityconnection.repository.CityRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataInitializer {

    private final CityRepository cityRepository;
    private final CityPairRepository cityPairRepository;

    public DataInitializer(CityRepository cityRepository, CityPairRepository cityPairRepository) {
        this.cityRepository = cityRepository;
        this.cityPairRepository = cityPairRepository;
    }

    @PostConstruct
    public void init() throws IOException {
        ClassPathResource resource = new ClassPathResource("City.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));

        Map<String, City> cityMap = new HashMap<>();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] cities = line.split(",");
            String city1Name = cities[0].trim();
            String city2Name = cities[1].trim();

            City city1 = cityMap.get(city1Name);
            if (city1 == null) {
                city1 = new City(city1Name);
                cityMap.put(city1Name, city1);
                cityRepository.save(city1);
            }

            City city2 = cityMap.get(city2Name);
            if (city2 == null) {
                city2 = new City(city2Name);
                cityMap.put(city2Name, city2);
                cityRepository.save(city2);
            }

            List<CityPair> existingPairs = cityPairRepository.findByOriginCityOrDestinationCity(city1, city2);
            if (existingPairs.isEmpty()) {
                CityPair cityPair = new CityPair(city1, city2);
                cityPairRepository.save(cityPair);
            }

            city1.addReachableCity(city2);
            city2.addReachableCity(city1);
            cityRepository.save(city1);
            cityRepository.save(city2);
        }

        reader.close();
    }

}
