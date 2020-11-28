package com.bachriwalid.worldgdp.service;

import com.bachriwalid.worldgdp.model.Country;
import com.bachriwalid.worldgdp.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getCountries(Map<String, Object> params) {
        Pageable pageable = PageRequest.of(((Integer) params.get("pageNo")), ((Integer) params.get("pageSize")), Sort.by((String) params.get("sortBy")));
        Page<Country> countryPage = countryRepository.findByNameContainsAndContinentContainsAndRegionContains(
                params.get("name").toString(), params.get("continent").toString(), params.get("region").toString(), pageable);
        countryPage.getTotalPages();
        if (countryPage.hasContent()) {
            return countryPage.getContent();
        } else
            return new ArrayList<>();
    }

    public Country getCountryByCode(String code) {
        Optional<Country> country = this.countryRepository.findById(code);
        return country.orElseThrow(RuntimeException::new);

    }

    public List<String> getContinents() {

        return this.countryRepository.findDistinctContinent();

    }

    public List<Country> getCountriesRegion(String region, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Country> page = this.countryRepository.findByRegion(region, pageable);
        if (page.hasContent()) {
            return page.getContent();
        }
        return new ArrayList<>();
    }

    public int getCountriesCount(Map<String, Object> params) {

        List<Country> countryPage = countryRepository.findByNameContainsAndContinentContainsAndRegionContains(
                params.get("name").toString(), params.get("continent").toString(), params.get("region").toString());
        return countryPage.size();
    }

    public Country editCountry(String code, Country country) {

        this.countryRepository.findById(code)
                .orElseThrow(RuntimeException::new);
        return this.countryRepository.save(country);

    }


    public List<String> getRegions() {
        return this.countryRepository.findDistinctRegion();
    }

    public List<String> getGovs() {
        return this.countryRepository.findDistinctGovs();
    }
}
