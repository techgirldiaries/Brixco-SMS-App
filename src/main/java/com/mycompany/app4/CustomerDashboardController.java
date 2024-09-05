/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.app4;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class CustomerDashboardController implements Initializable {
    
    @FXML
    private ListView<Service> serviceList;  
    
    private ServiceDAO serviceDAO;
    
    private final DataService dataService;   
    
    public CustomerDashboardController() throws SQLException {   
        
        this.serviceDAO = new ServiceDAO(); 
        this.dataService = new DataService();
                    
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        dataService.setServices(serviceDAO.getAllServices());
        // Populate the ListView with service data (retrieve from the database)
        List<Service> services = dataService.getServices();
        
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

    }    
    
}
