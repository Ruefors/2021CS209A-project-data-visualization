package com.dataviz.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class pageJumpController {

    @RequestMapping("/mcharts")
    public String toMainChart() {

        return "charts";
    }
    @RequestMapping("/dashTable")
    public String toDashTable() {

        return "index";
    }
}
