package com.demo.cityconnection.repository;

import com.demo.cityconnection.model.City;
import com.demo.cityconnection.model.CityPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityPairRepository extends JpaRepository<CityPair, Long> {
//    List<CityPair>  findAllByCity1_IdOrCity2_Id(Long city1Id, Long city2Id);

    List<CityPair> findByOriginCityOrDestinationCity(City origin, City dest);
}

