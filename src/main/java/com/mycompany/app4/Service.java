/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app4;

/**
 *
 * @author HP
 */
public class Service {
    private String name;
    private String description;
    private int estimatedDuration;
    private double cost;
    
    // Constructor
    public Service(String name, String description, int estimatedDuration, double cost) {
        this.name = name;
        this.description = description;
        this.estimatedDuration = estimatedDuration;
        this.cost = cost;        
    }

    // Getters and setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    
    public int getEstimatedDuration() {
        return estimatedDuration;
    }
    public void setEstimatedDuration(int estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }
    
    
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
}
