/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proxyimage;

/**
 *
 * @author dell
 */
public class Proxy implements Img {
    private String fileName;
    private Real realImage;

    public Proxy(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new Real(fileName);
        }
        realImage.display();
    }
}

