package com.dataviz.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class MetaData{
    public String iso_code;
    public String country;
    public Date date;
    public int new_cases;
    public int new_deaths;
    public int total_deaths;
    public int total_cases;
    private static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

    public MetaData(String iso_code, String country, String date, int new_cases, int new_deaths, int total_deaths, int total_cases) throws ParseException {
        this.iso_code = iso_code;
        this.country = country;
        this.date = dateformat.parse(date);
        this.new_cases = new_cases;
        this.new_deaths = new_deaths;
        this.total_deaths = total_deaths;
        this.total_cases = total_cases;
    }
}

public class CovidData {
    public ArrayList<MetaData> data;

    public ArrayList<MetaData> get_data() {
        return data;
    }

    public CovidData(){
        this.data = new ArrayList<>();
    }

    public void readData(String path, String sep) {
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(path));
            String line = reader.readLine();

            while((line = reader.readLine()) != null) {
                String[] cells = line.split(sep,-1);
                this.add_data(cells[0],cells[2],cells[3],cells[5],cells[8],cells[7],cells[4]);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public int fillZero(String s) {
        return s.equals("") ? 0:(int)Float.parseFloat(s);
    }

    public void add_data(String iso_code, String country, String date, String new_cases, String new_deaths, String total_deaths, String total_cases) throws ParseException {
        MetaData metadata = new MetaData(iso_code, country, date, fillZero(new_cases), fillZero(new_deaths), fillZero(total_deaths), fillZero(total_cases));
        this.data.add(metadata);
    }

    public void add_data(MetaData m){
        this.data.add(m);
    }

    //test
    public static void main(String[] args) {
        CovidData cd = new CovidData();
        cd.readData("D:\\gitlib\\2021CS209A-project-data-visualization\\data_viz\\src\\main\\resources\\static\\owid-covid-data.csv", ",");
        System.out.println(cd.data.size());
        int j = 0;
        while(true) {
            for (int i = 0; i < 20; i++) {
                MetaData m = cd.data.get(i + 20 * j);
                System.out.println(i + 20 * j);

            }
            j++;
        }    }
}
