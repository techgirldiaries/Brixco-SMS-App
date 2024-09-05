/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app4;

/**
 *
 * @author HP
 */
//import java.util.List;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class SQLiteCustomerDAO implements CustomerDAO {
    private final Connection connection;
    
    
    public SQLiteCustomerDAO() throws SQLException {
        this.connection = SQLiteConn.connect();
    }

    @Override
    public int insertCustomer(Customer customer) {
    String query = "INSERT INTO Customer (first_name, last_name, email_address, phone_number, town, state, country) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
        // JDBC code to insert customer data into the database        
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        // Set parameters for the PreparedStatement
        statement.setString(1, customer.getFirstName());
        statement.setString(2, customer.getLastName());
        statement.setString(3, customer.getEmailAddress());
        statement.setString(4, customer.getPhoneNumber());
        statement.setString(5, customer.getTown());
        statement.setString(6, customer.getState());
        statement.setString(7, customer.getCountry());

        // Execute the query
        statement.executeUpdate();

            // Retrieve the generated customer ID
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Failed to insert customer, no ID obtained.");
            }
    } catch (SQLException e) {
        System.err.println("Failed to insert customer: " + e.getMessage());
    }
    return 0;
}
    

    @Override
    public Customer getCustomerById(int customerID) {        
     String query = "SELECT * FROM Customer c "
                 + "LEFT JOIN CustomerAuthentication ca ON c.customer_id = ca.customer_id "
                 + "WHERE c.customer_id = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, customerID);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {                
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String emailAddress = resultSet.getString("email_address");
                String phoneNumber = resultSet.getString("phone_number");
                String country = resultSet.getString("country");
                String state = resultSet.getString("state");
                String town = resultSet.getString("town");
                return new Customer (firstName, lastName, emailAddress, phoneNumber, country, state, town);                                
            } else {
                // No customer found with the given ID
                return null;
            }
        }
    } catch (SQLException e) {
        // Handle any database errors
        e.printStackTrace();
        return null;
    }
    }
//
//    @Override
//    public List<Customer> getAllCustomers() {
//        // JDBC code to retrieve all customers from the database
//    }
//
//    @Override
//    public void updateCustomer(Customer customer) {
//        // JDBC code to update customer data in the database
//    }
//
//    @Override
//    public void deleteCustomer(int id) {
//        // JDBC code to delete customer data from the database
//    }
//}
}
