/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dependencyinjection;

/**
 *
 * @author dell
 */
public class CustomerService {
    private CustomerRepo repository;

    // Constructor injection
    public CustomerService(CustomerRepo repository) {
        this.repository = repository;
    }

    public String getCustomerDetails(int id) {
        return repository.findCustomerById(id);
    }
}

