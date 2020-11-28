package com.bachriwalid.worldgdp.controller;


import com.bachriwalid.worldgdp.model.Country;
import com.bachriwalid.worldgdp.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;


    @GetMapping()
    public ResponseEntity<List<Country>> getCountries(@RequestParam(name = "search", defaultValue = "") String searchTerm,
                                                      @RequestParam(name = "continent", defaultValue = "") String continent,
                                                      @RequestParam(name = "region", defaultValue = "") String region,
                                                      @RequestParam(defaultValue = "0") Integer pageNo,
                                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                                      @RequestParam(defaultValue = "code") String sortBy) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", searchTerm);
        params.put("region", region);
        params.put("continent", continent);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        params.put("sortBy", sortBy);
        List<Country> countries = this.countryService.getCountries(params);
        return new ResponseEntity<List<Country>>(countries, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{countryCode}")
    public ResponseEntity<Country> getCountryByCode(@PathVariable String countryCode) {
        Country country = this.countryService.getCountryByCode(countryCode);
        return new ResponseEntity<Country>(country, new HttpHeaders(), HttpStatus.OK);

    }
    /*@PutMapping(value = "/{countryCode}")
    public ResponseEntity<Country> editCountry(@PathVariable String countryCode,@RequestBody Country country){
        Country c = this.countryService.editCountry(countryCode,country);
        return ResponseEntity.ok(c);
    }*/
}
