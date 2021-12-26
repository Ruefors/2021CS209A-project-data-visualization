package com.dataviz.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VizData {
    public String date;
    public String label;
    public Map<String,Integer> data;
    public static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM");
    public VizData(String date,String label){
        this.date = date;
        this.label = label;
        data = new HashMap<>();
    }
}
