/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soap;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.*;
import com.apixu.weather.RetrieveWeatherData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.mysql.MySql;
/**
 *
 * @author zhout
 */
@WebService(serviceName = "Weather_Service")
public class Weather_Service {

    private JSONObject location;
    private JSONObject weather;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "setLocation")
    public void retrieveData(@WebParam(name = "location") String location) {
        RetrieveWeatherData ob = new RetrieveWeatherData();
        JSONObject data = ob.retrieveData(location);
        this.location = data.getJSONObject("location");
        this.weather = data.getJSONObject("current");
    }

    @WebMethod(operationName = "getCountry")
    public String getCountry() {
        String country = location.getString("country");
        return country;
    }

    @WebMethod(operationName = "getLocalTime")
    public String getLocalTime() {
        return location.getString("localtime");
    }

    @WebMethod(operationName = "getTemp_c")
    public Double getTemp_c() {
        return weather.getDouble("temp_c");
    }

    @WebMethod(operationName = "getTemp_f")
    public Double getTemp_f() {
        return weather.getDouble("temp_f");
    }

    @WebMethod(operationName = "getConditionText")
    public String getConditionText() {
        return weather.getJSONObject("condition").getString("text");
    }

    @WebMethod(operationName = "getConditionIcon")
    public String getConditionIcon() {
        return weather.getJSONObject("condition").getString("icon");
    }

    @WebMethod(operationName = "getWind_mph")
    public Double getWind_mph() {
        return weather.getDouble("wind_mph");
    }

    @WebMethod(operationName = "getWind_degree")
    public Double getWind_degree() {
        return weather.getDouble("wind_degree");
    }

    @WebMethod(operationName = "getWind_dir")
    public String getWind_dir() {
        return weather.getString("wind_dir");
    }

    @WebMethod(operationName = "getPressure_mb")
    public Double getPressure_mb() {
        return weather.getDouble("pressure_mb");
    }

    @WebMethod(operationName = "getHumidity")
    public Double getHumidity() {
        return weather.getDouble("humidity");
    }

    @WebMethod(operationName = "getVis_miles")
    public Double getVis_miles() {
        return weather.getDouble("vis_miles");
    }
}
