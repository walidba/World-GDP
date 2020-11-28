package com.bachriwalid.worldgdp.service;


import com.bachriwalid.worldgdp.model.City;
import com.bachriwalid.worldgdp.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public Iterable<City> getCities() {
        return this.cityRepository.findAll();
    }

    public List<City> getCountryCities(String code) {
        return (List<City>) this.cityRepository.findCitiesByCountry_Code(code);
    }

    public City getCityById(Long id) {
        return this.cityRepository.findById(id).get();
    }

    public City addCity(City city) {
        return this.cityRepository.save(city);
    }

    public void deleteCity(Long id) {
        this.cityRepository.deleteById(id);
    }

}
