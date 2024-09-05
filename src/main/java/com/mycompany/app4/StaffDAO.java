/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class StaffDAO {
    
    private final Connection connection;
    
    public StaffDAO() throws SQLException {
        this.connection = SQLiteConn.connect();
    }
    
    
    public boolean authenticateStaff(String username, String password) {
        String query = "SELECT * FROM staff WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // If a row is returned, authentication is successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
