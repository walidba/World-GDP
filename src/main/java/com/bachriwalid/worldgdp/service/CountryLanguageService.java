package com.bachriwalid.worldgdp.service;

import com.bachriwalid.worldgdp.model.CountryLanguage;
import com.bachriwalid.worldgdp.repository.CountryLanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryLanguageService {
    private final CountryLanguageRepository countryLanguageRepository;

    @Autowired
    public CountryLanguageService(CountryLanguageRepository countryLanguageRepository) {
        this.countryLanguageRepository = countryLanguageRepository;
    }


    public List<CountryLanguage> getCountryLanguages(String code) {
        List<CountryLanguage> languages;
        languages = this.countryLanguageRepository.findByCode(code);
        return languages;
    }

    public CountryLanguage addCountryLanguage(String code, CountryLanguage countryLanguage) {

        List<CountryLanguage> countryLanguages = this.getCountryLanguages(code);
        countryLanguages.forEach(language -> {
            if (language.getLanguage().equalsIgnoreCase(countryLanguage.getLanguage()))
                return;
        });
        return this.countryLanguageRepository.save(countryLanguage);
    }

    public void deleteCountryLanguage(String code, String lang) {
        this.countryLanguageRepository.deleteByCountryCodeAndAndLanguage(code, lang);
    }
}
