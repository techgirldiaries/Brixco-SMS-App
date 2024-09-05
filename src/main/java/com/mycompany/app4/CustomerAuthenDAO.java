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
public class CustomerAuthenDAO {
    
    private final Connection connection;
    
        
    /**
     *
     * @throws SQLException
     */
    public CustomerAuthenDAO() throws SQLException {
        this.connection = SQLiteConn.connect();
    }
    
    /**
     *
     * @param customerID
     * @param username
     * @param password
     * @throws SQLException
     */
    public void insertCustomerAuthen(int customerID, String username, String password) throws SQLException {
        String query = "INSERT INTO CustomerAuthentication (customer_id, username, password) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, customerID);
            statement.setString(2, username);
            statement.setString(3, password);
            
            statement.executeUpdate();
                      
            //System.out.println("Customer inserted successfully.");
        } catch (SQLException e) {
        System.err.println("Failed to insert customer: " + e.getMessage());}
    }
    
    public boolean authenticateCustomer(String username, String password) {
        String query = "SELECT * FROM CustomerAuthentication WHERE username = ? AND password = ?";
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
    
    
