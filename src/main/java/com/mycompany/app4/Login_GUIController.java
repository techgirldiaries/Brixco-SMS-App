/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.app4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class Login_GUIController implements Initializable {
    
    @FXML
    private TextField usernameTF;

    @FXML
    private TextField passwordTF;
    
    @FXML
    private Button btn_sign_up;            
    
    @FXML
    private Node node; // Reference to any node in the current scene
        
    private CustomerAuthenDAO customerAuthenDAO;
        
    private StaffDAO staffDAO;
    
    
    public Login_GUIController() throws SQLException {
        
        // Initialize customerDAO object
        try{
            this.customerAuthenDAO = new CustomerAuthenDAO();
            
            // Initialize staffDAO object
            this.staffDAO = new StaffDAO();
        } catch (SQLException e) {
            // If an exception occurs during initialization (e.g., database connectivity failure)
            // Display an error message to the user
            displayErrorMessage("Database Connection Error", "Failed to connect to the database. Please check your database configuration.");            
            e.printStackTrace();
        }  
    }
    
    @FXML
    private void switchToSecondFxml(ActionEvent event) throws IOException {
        Parent secondRoot = FXMLLoader.load(getClass().getResource("register.fxml"));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene secondScene = new Scene(secondRoot);        
        stage.setScene(secondScene);
        stage.show();
    }
    
    @FXML
    private void goToStaffDashboard() {
        // Get the Stage object from the current scene
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("staffDashboard.fxml"));
            Parent root = loader.load();

            // Create a new scene
            Scene scene = new Scene(root);

            // Set the new scene on the stage
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void goToLoginPage() {
        // Get the Stage object from the current scene
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login_GUI.fxml"));
            Parent root = loader.load();

            // Create a new scene
            Scene scene = new Scene(root);

            // Set the new scene on the stage
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    @FXML
    private void goToCustomerDashboard() {
        // Get the Stage object from the current scene
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("customerDashboard.fxml"));
            Parent root = loader.load();

            // Create a new scene
            Scene scene = new Scene(root);

            // Set the new scene on the stage
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    @FXML
    private void loginToDashboard() throws IOException, SQLException {
        
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        
        
        if (username.contains("@mysms.com")) {
            // Staff login
            if (staffDAO.authenticateStaff(username, password)) {
                // Close the current window
                // Open the dashboard 
                goToStaffDashboard();
                System.out.println("Login successful");                     
            } else {
                // Staff login failed
                System.out.println("Staff login failed");                
                displayErrorMessage("Error Logging In", "Try again or register.");
                
                // Reopen the login page
                goToLoginPage();
            }
        } else {
            // Retrieve customer Authentication details fron database
            
            if (customerAuthenDAO.authenticateCustomer(username, password)) {
                // Login successful
                goToCustomerDashboard(); 
                
                System.out.println("Login successful");

            } else {
                // Login failed
                // Display error message to the user
                displayErrorMessage("Error Logging In", "Try again or register.");
                // Reopen the login page
                goToLoginPage();
                System.out.println("Login failed");
            }
        } 
        
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

   // Method to display an error message
    private void displayErrorMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }    
}


