/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dependencyinjection;

/**
 *
 * @author dell
 */
public class Main {
    public static void main(String[] args) {
        CustomerRepo repository = new CustomerRepoImpl();
        
        CustomerService service = new CustomerService(repository);
        
        String customerDetails = service.getCustomerDetails(1);
        System.out.println(customerDetails);
    }
}

