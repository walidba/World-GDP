package com.bachriwalid.worldgdp.model;

public class CountryGDP {
    private Short year;
    private Double value;

    public CountryGDP() {

    }

    public CountryGDP(Short year, Double value) {
        this.year = year;
        this.value = value;
    }

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
