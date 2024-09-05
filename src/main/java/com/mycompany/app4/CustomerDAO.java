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
        
        
public interface CustomerDAO {

    /**
     *
     * @param customer
     * @return
     */
    int insertCustomer(Customer customer);
    Customer getCustomerById(int id);
//    List<Customer> getAllCustomers();
//    void updateCustomer(Customer customer);
//    void deleteCustomer(int id);
}
