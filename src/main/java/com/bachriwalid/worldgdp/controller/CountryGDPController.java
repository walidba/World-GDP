package com.bachriwalid.worldgdp.controller;


import com.bachriwalid.worldgdp.model.CountryGDP;
import com.bachriwalid.worldgdp.service.WorldBankApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/countries")
public class CountryGDPController {
    @Autowired
    private WorldBankApiClient worldBankApiClient;

    @GetMapping("/gdp/{countryCode}")
    public ResponseEntity<?> getGDP(@PathVariable String countryCode) {
        return new ResponseEntity<List<CountryGDP>>(this.worldBankApiClient.getGDP(countryCode), new HttpHeaders(), HttpStatus.OK);


    }


}
