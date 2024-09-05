/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class ServiceDAO {
    
    
    private final Connection connection;
    
    
    public ServiceDAO() throws SQLException {
        this.connection = SQLiteConn.connect();
    }
    
    
    /**
     *
     * @param service
     * @return
     */
    public int insertService(Service service) {
        String query = "INSERT INTO Service (name, description, estimated_duration, cost) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, service.getName());
            statement.setString(2, service.getDescription());
            statement.setInt(3, service.getEstimatedDuration());
            statement.setDouble(4, service.getCost());
            
            statement.executeUpdate();
            
            // Retrieve the generated customer ID
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Failed to insert service, no ID obtained.");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    // Retrieve all service records from the database
    public List<Service> getAllServices() {
        List<Service> services = new ArrayList<>();
        String query = "SELECT * FROM Service";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Service service = new Service(
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("estimated_duration"),
                        resultSet.getDouble("cost")
                );
                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }
    
    // Update an existing service record in the database
    public void updateService(Service service) {
        String query = "UPDATE Service SET name=?, description=?, estimated_duration=?, cost=? WHERE service_id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, service.getName());
            statement.setString(2, service.getDescription());
            statement.setInt(3, service.getEstimatedDuration());
            statement.setDouble(4, service.getCost());
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Delete an existing service record from the database
    public void deleteService(int serviceId) {
        String query = "DELETE FROM Service WHERE service_id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, serviceId);
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
