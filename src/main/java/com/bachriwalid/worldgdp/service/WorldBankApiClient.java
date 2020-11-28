package com.bachriwalid.worldgdp.service;

import com.bachriwalid.worldgdp.model.CountryGDP;
import com.google.gson.JsonParser;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import java.util.ArrayList;
import java.util.List;

@Service
public class WorldBankApiClient {
    String gdpUrl = "http://api.worldbank.org/v2/countries/%s/indicator/NY.GDP.MKTP.CD?" + "format=json&date=2008:2017";

    public List<CountryGDP> getGDP(String code) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(String.format(gdpUrl, code), String.class);

        JsonArray responseData = JsonParser.parseString(response.getBody()).getAsJsonArray();
        JsonArray countryDataArr = (JsonArray) responseData.get(1);
        List<CountryGDP> data = new ArrayList<>();
        JsonObject countryDataYearWise;
        for (int index = 0; index < countryDataArr.size(); index++) {
            countryDataYearWise = (JsonObject) countryDataArr.get(index);
            String valueStr = "0";
            if (countryDataYearWise.get("value") != null) {
                valueStr = countryDataYearWise.get("value").toString();
            }
            String yearStr = countryDataYearWise.get("date").toString();
            CountryGDP gdp = new CountryGDP();
            gdp.setValue(valueStr != null ? Double.valueOf(valueStr) : null);

            gdp.setYear(Short.valueOf(yearStr.replace("\"", "")));
            data.add(gdp);
        }
        return data;
    }
}
