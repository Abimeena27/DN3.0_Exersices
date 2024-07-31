/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.singleton;

/**
 *
 * @author dell
 */
public class logger {
    // Private static instance of the Logger class
    private static logger instance;

    // Private constructor to prevent instantiation
    private logger() {
        // Initialize any resources if needed
    }

    // Public static method to get the instance of the Logger class
    public static logger getInstance() {
        if (instance == null) {
            instance = new logger();
        }
        return instance;
    }

    // Method to log messages
    public void log(String message) {
        System.out.println("Log message: " + message);
    }
}

