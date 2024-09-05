/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app4;

/**
 *
 * @author HP
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage)throws Exception{        

        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login_GUI.fxml"));
        Parent root = loader.load();

        // Sets the FXML file as the root of the scene
        Scene scene = new Scene(root);

        // Sets the scene to the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Brixco Service Management System");
        primaryStage.show();
    }
    public static void main(String[] args) {
        //App.main(args);
        launch(args);
        
}   
}
    
 
    
    