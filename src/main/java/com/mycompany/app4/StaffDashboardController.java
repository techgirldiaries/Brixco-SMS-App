/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to
edit this template
 */
package com.mycompany.app4;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class StaffDashboardController implements Initializable {
        
    @FXML    
    private Button regButton; 
    
    @FXML    
    private TextField regFirstNameField;  
    
    @FXML    
    private TextField regLastNameField;  
    
    @FXML    
    private TextField regEmailAddressField;  
    
    @FXML    
    private TextField regPhoneNumberField;  
    
    @FXML
    private TextField regUsernameField;

    @FXML
    private TextField regPasswordField;  
    
    @FXML    
    private TextField regTownField;  
    
    @FXML    
    private TextField regStateField;  
    
    @FXML    
    private TextField regCountryField; 
    
    @FXML
    private ListView<Service> serviceList;    
    
    private ServiceDAO serviceDAO;
    
    private CustomerDAO customerDAO;
    
    private CustomerAuthenDAO customerAuthenDAO;
    
        
    /**
     *
     * @throws java.sql.SQLException */ 
    public StaffDashboardController() throws SQLException {   
        try {
            this.serviceDAO = new ServiceDAO();  
            
        } catch (SQLException e) {
            // If an exception occurs during initialization (e.g., database connectivity failure)                        
            e.printStackTrace();
        }
        
        // Initialize customerDAO object and authentication object.
        try{
            this.customerDAO = new SQLiteCustomerDAO() {};
            this.customerAuthenDAO = new CustomerAuthenDAO();
        } catch (SQLException e) {
            // If an exception occurs during initialization (e.g., database connectivity failure)
            // Display an error message to the user
            displayErrorMessage("Database Connection Error", "Failed to connect to the database. Please check your database configuration.");            
            e.printStackTrace();
        }  
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {                 
        // Populate the ListView with service data (retrieve from the database)
        List<Service> services = serviceDAO.getAllServices();
        
                
        // Populate the ListView with service data
        serviceList.getItems().addAll(services);
        
        // Set custom cell factory to display service details
        serviceList.setCellFactory(new Callback<ListView<Service>, ListCell<Service>>() {
            @Override
            public ListCell<Service> call(ListView<Service> param) {
                return new ListCell<Service>() {
                    @Override
                    protected void updateItem(Service service, boolean empty) {
                        super.updateItem(service, empty);
                        if (service == null || empty) {
                            setText(null);
                        } else {
                            Label label = new Label();
                            label.setWrapText(true); // Enable text wrapping
                            label.setMaxWidth(450); // Adjust the maximum width as needed
                            label.setText("Service: " + service.getName() + " \n" + "Description: " + service.getDescription() 
                                    + " \n" + "Estimated Duration: " + service.getEstimatedDuration() + "hour(s) \n" + "Cost: £" + service.getCost());
                            label.setStyle("-fx-content-display: top"); // Display content at the top
                            setGraphic(label);
                        }
                    }
                };
            }
        });
        
        // Disable registerButton until all text fields are filled
        regButton.setDisable(true);
        
        // Add listener to text fields to enable/disable registerButton
        regFirstNameField.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsFilled());
        regLastNameField.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsFilled());
        regEmailAddressField.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsFilled());
        regPhoneNumberField.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsFilled());
        regUsernameField.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsFilled());
        regPasswordField.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsFilled());
        regTownField.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsFilled());
        regStateField.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsFilled());
        regCountryField.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsFilled());
    }
    
    
    private void checkFieldsFilled() {
        // Enable registerButton only if all text fields are filled
        boolean allFieldsFilled = !regFirstNameField.getText().isEmpty() && !regLastNameField.getText().isEmpty() && 
                !regEmailAddressField.getText().isEmpty() && !regPhoneNumberField.getText().isEmpty() && !regUsernameField.getText().isEmpty() &&
                !regPasswordField.getText().isEmpty() && !regTownField.getText().isEmpty() && !regStateField.getText().isEmpty() &&
                !regCountryField.getText().isEmpty();
        
        regButton.setDisable(!allFieldsFilled);
    }
    
    
    private boolean areAllFieldsFilled() {
        // Check if all text fields are filled
        return !regFirstNameField.getText().isEmpty() && !regLastNameField.getText().isEmpty() && 
                !regEmailAddressField.getText().isEmpty() && !regPhoneNumberField.getText().isEmpty() && !regUsernameField.getText().isEmpty() &&
                !regPasswordField.getText().isEmpty() && !regTownField.getText().isEmpty() && !regStateField.getText().isEmpty() &&
                !regCountryField.getText().isEmpty();        
    }
    
    // Method to handle registration button click
    @FXML
    private void regButtonClicked() throws SQLException {
        // Check if all text fields are filled
        if (!areAllFieldsFilled()) {
            // Show alert if any field is empty
            displayErrorMessage("Error", "All fields are required. Please fill in all fields.");
            return;
        }
        
        // Retrieve input values
        String firstName = regFirstNameField.getText();
        String lastName = regLastNameField.getText();
        String emailAddress = regEmailAddressField.getText();
        String phoneNumber = regPhoneNumberField.getText();
        String username = regUsernameField.getText();
        String password = regPasswordField.getText();
        String town = regTownField.getText();
        String state = regStateField.getText();
        String country = regCountryField.getText();
                                         
        
        // create new Customer object
        Customer customer = new Customer(firstName, lastName, emailAddress, phoneNumber, town, state, country);
        
         
        int customerID = customerDAO.insertCustomer(customer);
        
        // insert customer data into database
        if (customerDAO != null) {            
            customerAuthenDAO.insertCustomerAuthen(customerID, username, password);  
            // Display success alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration Successful");
            alert.setHeaderText(null);
            alert.setContentText("User is registered!");
            alert.showAndWait();
            resetAll();
            
            
        } else {
            System.out.println("Unable to save!");
            }             
    }    
    
    // Method to clear all entries when "Clear All" button is clicked
    @FXML
    private void resetAll() {
        regFirstNameField.clear(); // Clear first name field
        regLastNameField.clear(); // Clear last name field
        regEmailAddressField.clear(); // Clear email address field
        regPhoneNumberField.clear(); // Clear phone number field
        regUsernameField.clear(); // Clear username field
        regPasswordField.clear(); // Clear password field
        regTownField.clear(); // Clear town field
        regStateField.clear(); // Clear state field
        regCountryField.clear(); // Clear country field        
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
    private void openAddService() throws IOException {
        // Load the FXML file of the new scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addService.fxml"));
        Parent root = loader.load();

        // Create a new stage for the new scene
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("New Service");

        // Show the new stage
        stage.show();
    }
}    
   
