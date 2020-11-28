package com.bachriwalid.worldgdp.repository;

import com.bachriwalid.worldgdp.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends PagingAndSortingRepository<Country, String> {

    @Query("SELECT DISTINCT continent FROM Country")
    List<String> findDistinctContinent();


    Optional<Country> findByCode(String code);

    Page<Country> findByRegion(String region, Pageable pageable);

    Page<Country> findByNameContainsAndContinentContainsAndRegionContains(String name, String continent, String region, Pageable pageable);

    @Query("SELECT distinct region from Country ")
    List<String> findDistinctRegion();

    List<Country> findByNameContainsAndContinentContainsAndRegionContains(String name, String continent, String region);

    @Query("SELECT distinct governmentForm from Country ")
    List<String> findDistinctGovs();
}
