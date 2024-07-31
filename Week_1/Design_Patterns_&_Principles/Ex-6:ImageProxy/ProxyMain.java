/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proxyimage;

/**
 *
 * @author dell
 */
public class ProxyMain {
    public static void main(String[] args) {
        Img image1 = new Proxy("test_image_1.jpg");
        Img image2 = new Proxy("test_image_2.jpg");

        image1.display();
        System.out.println();

        image1.display();
        System.out.println();

        image2.display();
        System.out.println();

        image2.display();
    }
}
