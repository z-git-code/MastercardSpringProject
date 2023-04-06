package com.demo.cityconnection.repository;

import com.demo.cityconnection.model.City;
import com.demo.cityconnection.model.CityPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    City findByCityName(String cityName);
}
