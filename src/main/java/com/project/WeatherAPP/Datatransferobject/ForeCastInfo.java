package com.project.WeatherAPP.Datatransferobject;

import java.util.ArrayList;

public class ForeCastInfo {
    private WeatherInfo weather ;
    private ArrayList<DayTemp> dayTemps;

    public ArrayList<DayTemp> getDayTemps() {
        return dayTemps;
    }

    public void setDayTemps(ArrayList<DayTemp> dayTemps) {
        this.dayTemps = dayTemps;
    }

    public WeatherInfo getWeather() {
        return weather;
    }

    public void setWeather(WeatherInfo weather) {
        this.weather = weather;
    }

}
