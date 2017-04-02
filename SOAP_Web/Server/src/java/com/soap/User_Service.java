/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soap;

import com.mysql.MySql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author zhout
 */
@WebService(serviceName = "User_Service")
public class User_Service {

    /**
     * This is a sample web service operation
     */
    private Connection con;
    private Statement st;
    private String username;
    
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
    
}
