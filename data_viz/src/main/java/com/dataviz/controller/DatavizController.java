package com.dataviz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.dataviz.model.CovidData;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import com.dataviz.model.DataFilter;
import java.util.zip.CheckedInputStream;

@Controller

public class DatavizController {
    DataFilter df;


    @RequestMapping(value = "/inde",method = RequestMethod.GET)
    @ResponseBody
    public CovidData hello() {
        df = new DataFilter();
        df.total_data = new CovidData();
        df.total_data.readData("D:\\gitlib\\2021CS209A-project-data-visualization\\data_viz\\src\\main\\resources\\static\\owid-covid-data.csv",",");
        CovidData curpage = df.getCurrentPages(1, 20);
        return curpage;
    }

    @GetMapping("/page")
    @ResponseBody
    public CovidData argParam(Integer ID) {
        int pageSize = 20;
        if(ID == df.total_data.data.size()/20+1){
            pageSize = df.total_data.data.size() - (ID-1)*20;
        }
        CovidData curpage = df.getCurrentPages(ID, pageSize);
        return curpage;
    }

    @GetMapping("/sort")
    @ResponseBody
    public String sort(String prop, String order) {
        System.out.println(prop);
        System.out.println(order);
        return "hhhh";
    }


    @RequestMapping(value = "/pageSize",method = RequestMethod.GET)
    @ResponseBody
    public int pagesize() {
        int s = df.total_data.data.size();
        return s;
    }



}