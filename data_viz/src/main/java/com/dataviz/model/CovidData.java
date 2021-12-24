package com.dataviz.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


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
    public static void main(String[] args) throws ParseException {
        CovidData cd = new CovidData();
        cd.readData("D:\\gitlib\\2021CS209A-project-data-visualization\\data_viz\\src\\main\\resources\\static\\owid-covid-data.csv", ",");
        System.out.println(cd.data.size());
        Collections.sort(cd.data,new Comparator<MetaData>(){
            public int compare(MetaData arg0, MetaData arg1) {
                return arg0.new_cases - arg1.new_cases;}
        });
        Collections.sort(cd.data,new Comparator<MetaData>(){
            public int compare(MetaData arg0, MetaData arg1) {
                return arg1.date.compareTo(arg0.date);}
        });

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2019-10-01");
        int j = date.getYear();
        System.out.println(j);
    }
}
