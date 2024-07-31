/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.paymentprocessor;

/**
 *
 * @author dell
 */
public class Main {
    public static void main(String[] args) {
        // Using PayPal payment gateway
        PayPal payPal = new PayPal();
        ProcessPayment payPalAdapter = new PayPalAdapter(payPal);
        payPalAdapter.processPayment(100.00);

        // Using Stripe payment gateway
        Stripe stripe = new Stripe();
        ProcessPayment stripeAdapter = new StripeAdapter(stripe);
        stripeAdapter.processPayment(200.00);
    }
}
