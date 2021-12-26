package com.dataviz.model;

import java.text.ParseException;

public class MetaData2{
    public String iso_code;
    public String country;
    public String date;
    public int new_cases;
    public int new_deaths;
    public int total_deaths;
    public int total_cases;


    public MetaData2(String iso_code, String country, String date, int new_cases, int new_deaths, int total_deaths, int total_cases) throws ParseException {
        this.iso_code = iso_code;
        this.country = country;
        this.date = date;
        this.new_cases = new_cases;
        this.new_deaths = new_deaths;
        this.total_deaths = total_deaths;
        this.total_cases = total_cases;
    }
}
