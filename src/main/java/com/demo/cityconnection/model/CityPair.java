package com.demo.cityconnection.model;

import javax.persistence.*;

@Entity
@Table(name = "city_pair")
public class CityPair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "origin_city_id", referencedColumnName = "id")
    private City originCity;

    @ManyToOne
    @JoinColumn(name = "destination_city_id", referencedColumnName = "id")
    private City destinationCity;

    public CityPair() {
    }

    public CityPair(City originCity, City destinationCity) {
        this.originCity = originCity;
        this.destinationCity = destinationCity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City getOriginCity() {
        return originCity;
    }

    public void setOriginCity(City originCity) {
        this.originCity = originCity;
    }

    public City getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(City destinationCity) {
        this.destinationCity = destinationCity;
    }


}
