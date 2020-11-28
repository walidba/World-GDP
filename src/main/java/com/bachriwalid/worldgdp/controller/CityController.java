package com.bachriwalid.worldgdp.controller;

import com.bachriwalid.worldgdp.model.City;
import com.bachriwalid.worldgdp.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {
    @Autowired
    private CityService cityService;


    @GetMapping()
    public Iterable<City> allCities() {
        return this.cityService.getCities();
    }

    @GetMapping("/country/{code}")
    public ResponseEntity<List<City>> getCountryCities(@PathVariable String code) {
        List<City> cities = this.cityService.getCountryCities(code);
        return new ResponseEntity<List<City>>(cities, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCity(@PathVariable Long id) {
        City city = this.cityService.getCityById(id);
        return new ResponseEntity<City>(city, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public City addCity(@RequestBody City city) {
        return this.cityService.addCity(city);
    }

    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable Long id) {
        this.cityService.deleteCity(id);
    }


}
