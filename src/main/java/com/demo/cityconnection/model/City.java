package com.demo.cityconnection.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city_name", unique = true)
    private String cityName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "city_reachable_cities",
            joinColumns = @JoinColumn(name = "city_id"),
            inverseJoinColumns = @JoinColumn(name = "reachable_city_id"))
    private List<City> reachableCities = new ArrayList<>();

    public City() {
    }

    public City(String cityName) {
        this.cityName = cityName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<City> getReachableCities() {
        return reachableCities;
    }

    public void setReachableCities(List<City> reachableCities) {
        this.reachableCities = reachableCities;
    }

    public void addReachableCity(City city) {
        this.reachableCities.add(city);
    }

    public void removeReachableCity(City city) {
        this.reachableCities.remove(city);
    }
}
