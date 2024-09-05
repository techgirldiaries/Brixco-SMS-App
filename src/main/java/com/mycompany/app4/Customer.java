/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app4;

/**
 *
 * @author HP
 */
public class Customer {
    private String first_name;
    private String last_name;    
    private String email_address;    
    private String phone_number;    
    private String town;
    private String state;
    private String country;
    

    // Constructor
    public Customer(String first_name, String last_name, String email_address, String phone_number, String town, String state,String country) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email_address = email_address;
        this.phone_number = phone_number;
        this.town = town;
        this.state = state;
        this.country = country;
    }

    // Getters and setters
    public String getFirstName() {
        return first_name;
    }
    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }
    
    
    public String getLastName() {
        return last_name;
    }
    public void setLastName(String last_name) {
        this.last_name = last_name;
    }
    
    
    public String getEmailAddress() {
        return email_address;
    }
    public void setEmailAddress(String email_address) {
        this.email_address = email_address;
    }
    
    
    public String getPhoneNumber() {
        return phone_number;
    }
    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }
    
    
    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }
    
    
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    
    
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
}
