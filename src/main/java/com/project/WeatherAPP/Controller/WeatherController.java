package com.project.WeatherAPP.Controller;

import com.project.WeatherAPP.Datatransferobject.ForeCast;
import com.project.WeatherAPP.Datatransferobject.ForeCastInfo;
import com.project.WeatherAPP.Datatransferobject.Root;
import com.project.WeatherAPP.Datatransferobject.WeatherInfo;

import com.project.WeatherAPP.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    private WeatherService weatherservice;

    @GetMapping("/{city}")
    public WeatherInfo getweather(@PathVariable String city){
        return weatherservice.getdata(city);
    }
    @GetMapping("/forecast")
    public ForeCastInfo getforecast(@RequestParam String city , @RequestParam String days ){
        return weatherservice.getforecast(city,days);
    }








}
