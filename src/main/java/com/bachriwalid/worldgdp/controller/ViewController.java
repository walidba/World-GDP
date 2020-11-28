package com.bachriwalid.worldgdp.controller;

import com.bachriwalid.worldgdp.model.Country;
import com.bachriwalid.worldgdp.service.CityService;
import com.bachriwalid.worldgdp.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class ViewController {
    @Autowired
    private CountryService countryService;
    @Autowired
    private CityService cityService;

    @GetMapping("/countries")
    public String countries(Model model,
                            @RequestParam(name = "search", defaultValue = "") String searchTerm,
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


        model.addAttribute("countries", this.countryService.getCountries(params));
        model.addAttribute("count", this.countryService.getCountriesCount(params));
        model.addAttribute("continents", this.countryService.getContinents());
        model.addAttribute("regions", this.countryService.getRegions());


        return "countries";

    }

    @GetMapping("/countries/{code}")
    public String countryDetail(@PathVariable String code, Model model) {
        model.addAttribute("c", this.countryService.getCountryByCode(code));
        return "country";
    }

    @GetMapping("/countries/{code}/form")
    public String editCountry(@PathVariable String code, Model model) {
        model.addAttribute("c", this.countryService.getCountryByCode(code));
        model.addAttribute("continents", this.countryService.getContinents());
        model.addAttribute("regions", this.countryService.getRegions());
        model.addAttribute("cities", cityService.getCountryCities(code));
        model.addAttribute("govs", this.countryService.getGovs());
        return "country-form";
    }

    @PostMapping(value = "/countries/{code}",
            consumes = {MediaType
                    .APPLICATION_JSON_VALUE})
    public String updateCountry(@PathVariable String code, @RequestBody Country country, Model model) {

        Country countr = this.countryService.editCountry(code, country);
        model.addAttribute("c", countr);
        return "country";
    }
}
