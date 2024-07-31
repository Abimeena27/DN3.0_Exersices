/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.observer;

/**
 *
 * @author dell
 */
public class ObserveMain {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        Observe mobileApp1 = new MobileApp("MobileApp1");
        Observe mobileApp2 = new MobileApp("MobileApp2");
        Observe webApp1 = new WebApp("WebApp1");

        stockMarket.registerObserver((Observer) mobileApp1);
        stockMarket.registerObserver((Observer) mobileApp2);
        stockMarket.registerObserver((Observer) webApp1);

        stockMarket.setStockPrice(100.0);
        System.out.println();

        stockMarket.setStockPrice(200.0);
        System.out.println();

        stockMarket.deregisterObserver((Observer) mobileApp2);
        stockMarket.setStockPrice(300.0);
    }
}
