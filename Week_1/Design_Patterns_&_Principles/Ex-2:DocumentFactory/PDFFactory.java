/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.factorymethodpattern;

/**
 *
 * @author dell
 */
public class PDFFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new PDF();
    }
}
