package com.demo.cityconnection.service;

//import com.demo.cityconnection.exceptions.CityNotFoundException;
import com.demo.cityconnection.model.City;
import com.demo.cityconnection.model.CityPair;
import com.demo.cityconnection.repository.CityPairRepository;
import com.demo.cityconnection.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CityConnectionServiceImpl implements CityConnectionService {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityPairRepository cityPairRepository;

    @Override
    public boolean areCitiesConnected(String origin, String destination) {
        if (origin == null || destination == null || origin.trim().isEmpty() || destination.trim().isEmpty()) {
            return false;
        }
        //System.out.println("At the beginning of the areCities: origin = "+ origin + ". Destination = " + destination );
        City originCity = cityRepository.findByCityName(origin.trim());
        City destCity = cityRepository.findByCityName(destination.trim());
        if (originCity == null || destCity == null) {
            return false;
        }
        return bfs(originCity, destCity);
    }

    private boolean bfs(City origin, City destination) {
        Queue<City> queue = new LinkedList<>();
        Set<City> visited = new HashSet<>();
        queue.add(origin);
        visited.add(origin);
        while (!queue.isEmpty()) {
            City city = queue.poll();
            //System.out.println("In bfs(). Now the poll city name is: " + city.getCityName());
            if (city.equals(destination)) {
                return true;
            }
            List<City> neighbors = getNeighbors(city);
            //System.out.println("will jump to the getNeighbors method -->");
            //System.out.println("Let's see the poll city's neighbors: ");
            for (City neighbor : neighbors) {
                //System.out.println("In the loop of city's neighbor. neighbor's name = " + neighbor.getCityName());
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return false;
    }

    private List<City> getNeighbors(City city) {
        //System.out.println("At getNeighbors method");
        List<City> neighbors = new ArrayList<>();
        List<CityPair> cityPairs = cityPairRepository.findByOriginCityOrDestinationCity(city, city);
        for (CityPair cityPair : cityPairs) {
            if (!cityPair.getOriginCity().equals(city)) {
                neighbors.add(cityPair.getOriginCity());
            }
            if (!cityPair.getDestinationCity().equals(city)) {
                neighbors.add(cityPair.getDestinationCity());
            }
        }
        return neighbors;
    }
}
