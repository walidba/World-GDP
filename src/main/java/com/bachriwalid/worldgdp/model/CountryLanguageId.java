package com.bachriwalid.worldgdp.model;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class CountryLanguageId implements Serializable {

    @Id
    @ManyToOne
    private Country country;

    public CountryLanguageId() {

    }

    public CountryLanguageId(Country country) {
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
