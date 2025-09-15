package com.project.WeatherAPP.Datatransferobject;

public class WeatherInfo {
    private String City;
    private String Region;
    private String Country;
    private double temp ;
    private String condition;


    public WeatherInfo(String city, String region, String country, double temp, String condition) {
        City = city;
        Region = region;
        Country = country;
        this.temp = temp;
        this.condition = condition;
    }

    public WeatherInfo() {
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }


}
