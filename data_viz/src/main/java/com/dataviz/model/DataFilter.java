package com.dataviz.model;


public class DataFilter {
    // every page contains 20 entries

    public CovidData total_data;

    public CovidData getCurrentPages(int pages, int pageSize){
        CovidData page_data = new CovidData();
        for(int i=0;i<pageSize;i++){
            page_data.add_data(this.total_data.data.get(i+20*(pages-1)));
        }
        return page_data;
    }
}
