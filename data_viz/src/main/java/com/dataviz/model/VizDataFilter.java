package com.dataviz.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class VizDataFilter {
    public Map<String,VizData> totalCase;
    public Map<String,VizData> newCase;
    public Map<String,VizData> newDeath;
    public Map<String,VizData> totalDeath;

    public VizDataFilter(){
        totalCase = new HashMap<>();
        newCase = new HashMap<>();
        newDeath = new HashMap<>();
        totalDeath= new HashMap<>();
    }
    public ArrayList<VizData> sort(Map<String,VizData> map){
        ArrayList<VizData> res =new ArrayList<>(map.values());
        res.sort((o1, o2) -> {
            try {
                return VizData.dateformat.parse(o1.date).compareTo(VizData.dateformat.parse(o2.date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return 0;
        });
        return res;
    }
    public void processingTotalCase(DataFilter df){
        for(MetaData d : df.original_data.data) {
            if (totalCase.containsKey(VizData.dateformat.format(d.date))) {
                VizData t = totalCase.get(VizData.dateformat.format(d.date));
                if (t.data.containsKey(d.country)) {
                    if (d.total_cases > t.data.get(d.country)) {
                        t.data.replace(d.country, d.total_cases);
                    }
                } else {
                    t.data.put(d.country, d.total_cases);
                }
            } else {
                VizData v = new VizData(VizData.dateformat.format(d.date), "totalCase");
                v.data.put(d.country, d.total_cases);
                totalCase.put(VizData.dateformat.format(d.date), v);
            }
        }
    }
    public void processingNewCase(DataFilter df){
        for(MetaData d : df.original_data.data) {
            if (newCase.containsKey(VizData.dateformat.format(d.date))) {
                VizData t = newCase.get(VizData.dateformat.format(d.date));
                if (t.data.containsKey(d.country)) {
                    int num = t.data.get(d.country);
                    t.data.replace(d.country,num+d.new_cases);
                } else {
                    t.data.put(d.country, d.new_cases);
                }
            } else {
                VizData v = new VizData(VizData.dateformat.format(d.date), "newCase");
                v.data.put(d.country, d.new_cases);
                newCase.put(VizData.dateformat.format(d.date), v);
            }
        }
    }
    public void processingNewDeath(DataFilter df){
        for(MetaData d : df.original_data.data) {
            if (newDeath.containsKey(VizData.dateformat.format(d.date))) {
                VizData t = newDeath.get(VizData.dateformat.format(d.date));
                if (t.data.containsKey(d.country)) {
                    int num = t.data.get(d.country);
                    t.data.replace(d.country,num+d.new_deaths);
                } else {
                    t.data.put(d.country, d.new_deaths);
                }
            } else {
                VizData v = new VizData(VizData.dateformat.format(d.date), "newDeaths");
                v.data.put(d.country, d.new_deaths);
                newDeath.put(VizData.dateformat.format(d.date), v);
            }
        }
    }
    public void processingTotalDeath(DataFilter df){
        for(MetaData d : df.original_data.data) {
            if (totalDeath.containsKey(VizData.dateformat.format(d.date))) {
                VizData t = totalDeath.get(VizData.dateformat.format(d.date));
                if (t.data.containsKey(d.country)) {
                    if (d.total_deaths > t.data.get(d.country)) {
                        t.data.replace(d.country, d.total_deaths);
                    }
                } else {
                    t.data.put(d.country, d.total_deaths);
                }
            } else {
                VizData v = new VizData(VizData.dateformat.format(d.date), "totalDeath");
                v.data.put(d.country, d.total_deaths);
                totalDeath.put(VizData.dateformat.format(d.date), v);
            }
        }
    }
    class DateData{
        public String name;
        public int value;
        public DateData(String name,int value){
            this.name = name;
            this.value = value;
        }
    }
    public ArrayList<DateData> searchData(String country,String type){
        ArrayList<DateData> res = new ArrayList<>();
        if(type.equals("Total Case")){
            ArrayList<VizData> tmp = sort(totalCase);
            for(VizData v : tmp){
                if(v.data.containsKey(country)){
                    res.add(new DateData(v.date, v.data.get(country)));
                }
            }
        }
        else if(type.equals("New Case")){
            ArrayList<VizData> tmp = sort(newCase);
            for(VizData v : tmp){
                if(v.data.containsKey(country)){
                    res.add(new DateData(v.date, v.data.get(country)));
                }
            }
        }
        else if(type.equals("New Death")){
            ArrayList<VizData> tmp = sort(newDeath);
            for(VizData v : tmp){
                if(v.data.containsKey(country)){
                    res.add(new DateData(v.date, v.data.get(country)));
                }
            }
        }
        else if(type.equals("Total Death")){
            ArrayList<VizData> tmp = sort(totalDeath);
            for(VizData v : tmp){
                if(v.data.containsKey(country)){
                    res.add(new DateData(v.date, v.data.get(country)));
                }
            }
        }
        return res;
    }
}
