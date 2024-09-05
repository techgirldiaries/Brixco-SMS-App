/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.app4;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AddServiceController implements Initializable {
        
    @FXML    
    private Button cancelButton;
    
    @FXML
    private TextField serviceNameField;
    
    @FXML
    private TextField serviceDescriptionField;
    
    @FXML
    private TextField estimatedDurationField;
    
    @FXML
    private TextField costField;
    
    private ServiceDAO serviceDAO; // Inject ServiceDAO dependency
    

   
    
    // Setter method for ServiceDAO
    public AddServiceController () throws SQLException {
        // Initialize customerDAO object and authentication object.
        try{
            this.serviceDAO = new ServiceDAO();
        } catch (SQLException e) {
            // If an exception occurs during initialization (e.g., database connectivity failure)
            // Display an error message to the user
            displayErrorMessage("Database Connection Error", "Failed to connect to the database. Please check your database configuration.");            
            e.printStackTrace();
    }     
    }
    
    // Method to display an error message
    private void displayErrorMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    
    @FXML
    private void cancelButtonDo() {
        // Close the current window
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
        public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
        
    @FXML
    private void addService() {       
        try {
            int estimated_duration = Integer.parseInt(estimatedDurationField.getText());
            double cost = Double.parseDouble(costField.getText());

            Service newService = new Service(serviceNameField.getText(), serviceDescriptionField.getText(), estimated_duration, cost);

            int insertedServiceId = serviceDAO.insertService(newService);

            if (insertedServiceId != -1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Service Added");
                alert.setHeaderText(null);
                alert.setContentText("The service has been added successfully!");
                alert.showAndWait();

                serviceNameField.clear();
                serviceDescriptionField.clear();
                estimatedDurationField.clear();
                costField.clear();
            } else {
                System.err.println("Error: serviceDAO is not initialized.");
            }
        } catch (NumberFormatException e) {
            // Handle the case when the estimated duration or cost is not a valid number
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Cost and duration must be numeric values.");
            alert.showAndWait();
        }
               
    }

        
}
    

