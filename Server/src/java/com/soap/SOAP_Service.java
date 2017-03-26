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
@WebService(serviceName = "SOAP_Service")
public class SOAP_Service {

    private JSONObject location;
    private JSONObject weather;
    private String username;
    private Connection con;
    private Statement st;
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

    @WebMethod
    public boolean validUser(@WebParam(name = "username") String username,
                             @WebParam(name="password") String password) {
        this.username = username;
        String password2="";
        try{
            MySql sql = new MySql();
            this.con = sql.createConnection();
            this.st = con.createStatement();
            String query = "select password from user where username='" + username + "'";
            ResultSet rs=st.executeQuery(query);

            rs.next();
            password2 = rs.getString(1);

        }catch(Exception e) { System.out.println(e);}

        if (password.equals(password2)) {
            return true;
        } else {
            return false;
        }
    }
    
    @WebMethod
    public String getUser_saved_places() {
        String lastSavedPlacs = "";
        try{
            String query = "select saved_place from user_saved_places where username='" + this.username + "'";
            ResultSet rs = this.st.executeQuery(query);
            rs.next();
            
            lastSavedPlacs = rs.getString(1);
            this.con.close();
        }catch(Exception e) { System.out.println(e);}

        return lastSavedPlacs;
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
