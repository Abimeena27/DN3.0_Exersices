/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.singleton;

/**
 *
 * @author dell
 */
public class Main {
      public static void main(String[] args) {
        // Get the instance of Logger
        logger logger1 = logger.getInstance();
        logger logger2 = logger.getInstance();

        // Log messages
        logger1.log("This is the first log message.");
        logger2.log("This is the second log message.");

        // Check if both instances are the same
        if (logger1 == logger2) {
            System.out.println("Both logger1 and logger2 are the same instance.");
        } else {
            System.out.println("logger1 and logger2 are different instances.");
        }
    }
}
