package com.bachriwalid.worldgdp.controller;


import com.bachriwalid.worldgdp.model.CountryLanguage;
import com.bachriwalid.worldgdp.service.CountryLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
public class CountryLanguageController {
    @Autowired
    private CountryLanguageService countryLanguageService;

    @GetMapping("/{code}")
    public List<CountryLanguage> getCountryLanguages(@PathVariable String code) {
        return this.countryLanguageService.getCountryLanguages(code);
    }

    @DeleteMapping("/{code}/language/{lang}")
    public void deleteCountryLanguage(@PathVariable("code") String code, @PathVariable("lang") String lang) {
        this.countryLanguageService.deleteCountryLanguage(code, lang);
    }


}
