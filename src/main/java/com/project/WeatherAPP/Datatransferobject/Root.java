package com.project.WeatherAPP.Datatransferobject;

public class Root {
    private Location location;
    private Current current;
    private ForeCast forecast;

    public Root(Location location, Current current, ForeCast forecast) {
        this.location = location;
        this.current = current;
        this.forecast = forecast;
    }

    public ForeCast getForecast() {
        return forecast;
    }

    public void setForecast(ForeCast forecast) {
        this.forecast = forecast;
    }

    public Root() {
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
