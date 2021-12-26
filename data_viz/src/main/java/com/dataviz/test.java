package com.dataviz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        Date date;
       SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM");
        try {
            date = dateformat.parse("2021-10-24");
            dateformat1.format(date);
            System.out.println(dateformat1.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
