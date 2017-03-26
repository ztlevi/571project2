/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysql;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author zhout
 */
public class MySql {
       private Connection con;
       
    public Connection createConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con =DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/user_weather","root","");
//here sonoo is database name, root is username and password
        } catch(Exception e) { System.out.println(e);}
        this.con = con;
        return con;
    }
    
    public void closeDB(){
        try {
            this.con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
