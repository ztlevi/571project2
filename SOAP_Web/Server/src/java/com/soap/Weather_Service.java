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

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "setLocation")
    public String retrieveData(@WebParam(name = "location") String location) {
        RetrieveWeatherData ob = new RetrieveWeatherData();
        JSONObject data = ob.retrieveData(location);
        return data.toString();
    }
}
