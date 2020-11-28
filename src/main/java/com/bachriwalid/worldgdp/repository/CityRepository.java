package com.bachriwalid.worldgdp.repository;

import com.bachriwalid.worldgdp.model.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Long> {
    Iterable<City> findCitiesByCountry_Code(String countryCode);
}
