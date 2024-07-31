/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.factorymethodpattern;

/**
 *
 * @author dell
 */
public class PDF implements Document{
    @Override
    public void open() {
        System.out.println("Opening PDF document...");
    }

    @Override
    public void close() {
        System.out.println("Closing PDF document...");
    }

    @Override
    public void save() {
        System.out.println("Saving PDF document...");
    }
}
