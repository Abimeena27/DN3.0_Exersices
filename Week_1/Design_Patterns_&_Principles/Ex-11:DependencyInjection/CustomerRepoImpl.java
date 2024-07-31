/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dependencyinjection;

/**
 *
 * @author dell
 */
public class CustomerRepoImpl implements CustomerRepo {
    @Override
    public String findCustomerById(int id) {
        return "Customer with ID: " + id;
    }
}

