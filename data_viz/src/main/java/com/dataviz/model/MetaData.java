package com.dataviz.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MetaData{
    public String iso_code;
    public String country;
    public Date date;
    public String sdate;
    public int new_cases;
    public int new_deaths;
    public int total_deaths;
    public int total_cases;
    private static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

    public MetaData(String iso_code, String country, String date, int new_cases, int new_deaths, int total_deaths, int total_cases) throws ParseException {
        this.iso_code = iso_code;
        this.country = country;
        this.date = dateformat.parse(date);
        this.sdate = date;
        this.new_cases = new_cases;
        this.new_deaths = new_deaths;
        this.total_deaths = total_deaths;
        this.total_cases = total_cases;
    }
}
