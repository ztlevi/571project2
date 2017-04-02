/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apixu.weather;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.*;
/**
 *
 * @author zhout
 */
public class RetrieveWeatherData {
private String httpUrl = "http://api.apixu.com/v1/current.json?key=26defbaebba24aadbb813700172603";
public JSONObject retrieveData(String country){
        try {
            URL url = new URL(httpUrl + "&q=" + country);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                if (conn.getResponseCode() != 200) {
                        throw new RuntimeException("Failed : HTTP error code : "
                                                   + conn.getResponseCode());
                }

                BufferedReader br = new BufferedReader(new InputStreamReader(
                                                               (conn.getInputStream())));

                String output;
                System.out.println("Output from Server .... \n");

                JSONObject obj = new JSONObject(br.readLine());
                conn.disconnect();
                return obj;

        } catch (MalformedURLException e) {

                e.printStackTrace();

        } catch (IOException e) {

                e.printStackTrace();

        }
        return null;
}



}
