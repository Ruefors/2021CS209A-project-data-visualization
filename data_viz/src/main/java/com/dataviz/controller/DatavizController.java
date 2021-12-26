package com.dataviz.controller;

import com.dataviz.model.MetaData;
import com.dataviz.model.MetaData2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.ArrayList;
import com.dataviz.model.DataFilter;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.zip.CheckedInputStream;

@Controller

public class DatavizController {
    DataFilter df;
    VizDataFilter vf;
    public DatavizController(){
        df = new DataFilter();
        vf = new VizDataFilter();
        df.original_data= new CovidData();
        df.original_data.readData("C:\\Learning\\大四（上）\\2021CS209A-project-data-visualization\\data_viz\\src\\main\\resources\\static\\owid-covid-data.csv",",");
        vf.processingTotalCase(df);
        vf.processingNewCase(df);
        vf.processingNewDeath(df);
        vf.processingTotalDeath(df);
    }
    @RequestMapping(value = "/inde",method = RequestMethod.GET)
    @ResponseBody
    public CovidData hello() {
        df.current_data = df.original_data;
        Collections.sort(df.current_data.data,new Comparator<MetaData>(){
            public int compare(MetaData arg0, MetaData arg1) {
                return arg0.iso_code.compareTo(arg1.iso_code);
            }
        });
        CovidData curpage = df.getCurrentPages(1, 20);
        return curpage;
    }

    @GetMapping("/page")
    @ResponseBody
    public CovidData argParam(Integer ID) {
        int pageSize = 20;
        if(ID == df.current_data.data.size()/20+1){
            pageSize = df.current_data.data.size() - (ID-1)*20;
        }
        CovidData curpage = df.getCurrentPages(ID, pageSize);
        return curpage;
    }

    @GetMapping("/sort")
    @ResponseBody
    public String sort(String prop, String order) {
        df.sort_col = prop;
        df.order = order;
        df.SortData();
        return "hhh";
    }

    @GetMapping("/search")
    @ResponseBody
    public String search(String Type, String Content) {
        System.out.println(Type);
        System.out.println(Content);
        df.Search(Integer.parseInt(Type), Content);
        return "hhh1";
    }

    @RequestMapping(value = "/pageSize",method = RequestMethod.GET)
    @ResponseBody
    public int pagesize() {
        int s = df.current_data.data.size();
        return s;
    }

    @RequestMapping(value = "/totalData",method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<MetaData2> getTotal() throws ParseException {
        CovidData c = new CovidData();
        ArrayList<MetaData2> re= new ArrayList<>();
        for(MetaData m:df.current_data.data){
            MetaData2 tmp = new MetaData2(m.iso_code,m.country,m.sdate,m.new_cases,m.new_deaths,m.total_deaths,m.total_cases);
            re.add(tmp);
        }
        return re;
    }

    @RequestMapping(value = "/earth",method = RequestMethod.GET)
    public String imgEarth(){
        return "C:\\Learning\\大四（上）\\2021CS209A-project-data-visualization\\data_viz\\src\\main\\resources\\templates\\imge\\bg4.jpg";
    }
    @RequestMapping(value = "/total_cases",method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<VizData> getTotalCases(){
        return vf.sort(vf.totalCase);
    }
    @RequestMapping(value = "/new_cases",method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<VizData> getNewCases(){
        return vf.sort(vf.newCase);
    }
    @RequestMapping(value = "/new_deaths",method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<VizData> getNewDeaths(){
        return vf.sort(vf.newDeath);
    }
    @RequestMapping(value = "/total_deaths",method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<VizData> getTotalDeaths(){
        return vf.sort(vf.totalDeath);
    }

}