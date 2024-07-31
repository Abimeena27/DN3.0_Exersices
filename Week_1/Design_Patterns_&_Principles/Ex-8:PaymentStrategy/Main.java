/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.strategypattern;

/**
 *
 * @author dell
 */
public class Main {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        PaymentStrategy creditCardPayment = new CreditCardPayment("1234567890123456", "John Doe", "123", "12/25");
        context.setPaymentStrategy(creditCardPayment);
        context.executePayment(100.0);

        System.out.println();

        PaymentStrategy payPalPayment = new PayPalPayment("john.doe@example.com", "password123");
        context.setPaymentStrategy(payPalPayment);
        context.executePayment(200.0);
    }
}

