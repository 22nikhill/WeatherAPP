package com.project.WeatherAPP.Service;

import com.project.WeatherAPP.Datatransferobject.*;
import com.project.WeatherAPP.WeatherAppApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class WeatherService {

    @Value("${weather.url}")
    private String url;
    @Value("${api.key}")
    private String key;
    @Value("${forecast.url}")
    public String forecasturl;


    RestTemplate template = new RestTemplate();



    public WeatherInfo getdata(String City){
        String apiurl = url + "?key="+key+"&q="+City;
        Root response =template.getForObject(apiurl,Root.class);
        WeatherInfo info = new WeatherInfo();
        info.setCity( response.getLocation().getName());
        info.setRegion(response.getLocation().getRegion());
       info.setCountry(response.getLocation().getCountry());
       info.setTemp( response.getCurrent().getTemp_c());
        info.setCondition(response.getCurrent().getCondition().getText());

        return info;

    }
    public ForeCastInfo getforecast(String city , String days ){
        ForeCastInfo foreCastInfo = new ForeCastInfo();

        foreCastInfo.setWeather(getdata(city));

        String url = forecasturl+"?key="+key+"&q="+city+"&q="+days;

        Root response = template.getForObject(url,Root.class);


        ArrayList<DayTemp>  dayTemps = new ArrayList<>();



        for(ForeCastday foreCastday : response.getForecast().getForecastday()){

            DayTemp day = new DayTemp();
            day.setDate(foreCastday.getDate());
            day.setMax_temp(foreCastday.day.getMaxtemp_c());
            day.setMin_temp(foreCastday.day.getMintemp_c());
            dayTemps.add(day);

        }
        foreCastInfo.setDayTemps(dayTemps);

        return foreCastInfo;





    }

}
