package com.dataviz.model;


import java.text.ParseException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class DataFilter {
    // every page contains 20 entries

    public CovidData original_data;
    public CovidData current_data;

    public CovidData search_result;

    public String sort_col = "";
    public String order = "";

    public CovidData getCurrentPages(int pages, int pageSize){
        CovidData page_data = new CovidData();
        for(int i=0;i<pageSize;i++){
            page_data.add_data(this.current_data.data.get(i+20*(pages-1)));
        }
        return page_data;
    }

    public void Search(int type, String content){
        this.current_data = this.original_data;
        if(content.equals("")) {

            return;
        }
        switch(type){
            case 1 :  //国家名称
                this.search_result = new CovidData();
                for(MetaData m : this.current_data.data){
                    if(m.country.equalsIgnoreCase(content)){
                        this.search_result.data.add(m);
                    }
                }
                this.current_data = this.search_result;
                break;
            case 2 :  // 日期
                System.out.println("jjj");
                String [] cdate;
                if(content.contains("-")) {
                    cdate = content.split("-");
                }
                else{
                    cdate = content.split("/");
                }

                this.search_result = new CovidData();
                for(MetaData m : this.current_data.data){
                    String[] date = m.sdate.split("-");
                    boolean k = true;
                    for(int i=0;i<cdate.length;i++){
                        if (!date[i].equals(cdate[i])) {
                            k = false;
                            break;
                        }
                    }
                    if(k) {
                        System.out.println("==========");
                        this.search_result.add_data(m);
                    }
                }
                this.current_data = this.search_result;
                break;
            default : // iso_code
                this.search_result = new CovidData();
                for(MetaData m : this.current_data.data){
                    if(m.iso_code.equalsIgnoreCase(content)){
                        this.search_result.data.add(m);
                    }
                }
                this.current_data = this.search_result;

        }
    }

    public void SortData(){
        if(this.order == null){
            this.sort_col = "";
            this.order = "";
            this.current_data.data.sort(new Comparator<MetaData>() {
                public int compare(MetaData arg0, MetaData arg1) {
                    return arg0.iso_code.compareTo(arg1.iso_code);
                }
            });
            return;
        }

        if(this.sort_col.equals("new_cases")){
            if(this.order.equals("ascending")){
                this.current_data.data.sort(new Comparator<MetaData>() {
                    public int compare(MetaData arg0, MetaData arg1) {
                        return arg1.new_cases - arg0.new_cases;
                    }
                });
            }
            else if(this.order.equals("descending")){
                this.current_data.data.sort(new Comparator<MetaData>() {
                    public int compare(MetaData arg0, MetaData arg1) {
                        return arg0.new_cases - arg1.new_cases;
                    }
                });
            }
        }
        else if(this.sort_col.equals("sdate")){
            if(this.order.equals("ascending")){
                this.current_data.data.sort((arg0, arg1) -> arg1.date.compareTo(arg0.date));
            }
            else if(this.order.equals("descending")){
                this.current_data.data.sort(Comparator.comparing(arg0 -> arg0.date));
            }
        }
        else if(this.sort_col.equals("new_deaths")){
            if(this.order.equals("ascending")){
                Collections.sort(this.current_data.data,new Comparator<MetaData>(){
                    public int compare(MetaData arg0, MetaData arg1) {
                        return arg1.new_deaths-arg0.new_deaths;}
                });
            }
            else if(this.order.equals("descending")){
                Collections.sort(this.current_data.data,new Comparator<MetaData>(){
                    public int compare(MetaData arg0, MetaData arg1) {
                        return arg0.new_deaths-arg1.new_deaths;}
                });
            }
        }
        else if(this.sort_col.equals("total_cases")){
            if(this.order.equals("ascending")){
                Collections.sort(this.current_data.data,new Comparator<MetaData>(){
                    public int compare(MetaData arg0, MetaData arg1) {
                        return arg1.total_cases-arg0.total_cases;}
                });
            }
            else if(this.order.equals("descending")){
                Collections.sort(this.current_data.data,new Comparator<MetaData>(){
                    public int compare(MetaData arg0, MetaData arg1) {
                        return arg0.total_cases-arg1.total_cases;}
                });
            }
        }
        else if(this.sort_col.equals("total_deaths")){
            if(this.order.equals("ascending")){
                Collections.sort(this.current_data.data,new Comparator<MetaData>(){
                    public int compare(MetaData arg0, MetaData arg1) {
                        return arg1.total_deaths-arg0.total_deaths;}
                });
            }
            else if(this.order.equals("descending")){
                Collections.sort(this.current_data.data,new Comparator<MetaData>(){
                    public int compare(MetaData arg0, MetaData arg1) {
                        return arg0.total_deaths-arg1.total_deaths;}
                });
            }
        }

    }
}
