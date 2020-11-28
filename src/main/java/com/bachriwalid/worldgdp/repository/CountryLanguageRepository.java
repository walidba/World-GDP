package com.bachriwalid.worldgdp.repository;

import com.bachriwalid.worldgdp.model.CountryLanguage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountryLanguageRepository extends CrudRepository<CountryLanguage, String> {

    @Query(value = "SELECT * FROM countrylanguage WHERE countrycode= :code order by Percentage DESC", nativeQuery = true)
    List<CountryLanguage> findByCode(@Param("code") String code);


    void deleteByCountryCodeAndAndLanguage(String code, String lang);

//List<CountryLanguage> findByPk_Country_Code(String code);
    //Iterable<CountryLanguage> findByPkCountry_Code


}
