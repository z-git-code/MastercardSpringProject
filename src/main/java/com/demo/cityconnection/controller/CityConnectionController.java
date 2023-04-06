package com.demo.cityconnection.controller;

import com.demo.cityconnection.service.CityConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityConnectionController {
    @Autowired
    private CityConnectionService cityService;

    @GetMapping("/connected")
    public String areCitiesConnected(@RequestParam("origin") String origin,
                                     @RequestParam("destination") String destination) {
        boolean connected = cityService.areCitiesConnected(origin, destination);
        return connected ? "yes" : "no";
    }
}
