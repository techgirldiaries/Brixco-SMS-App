/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app4;

/**
 *
 * @author HP
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConn {
    //SQLite connection string
    private static final String url = "jdbc:sqlite:C:\\Users\\HP\\Documents\\NetbeansProjects2\\App4\\src\\main\\resources\\com\\mycompany\\app4\\sms.db";
    
    public static Connection connect() throws SQLException {        
        return DriverManager.getConnection(url);
    }
        
}

