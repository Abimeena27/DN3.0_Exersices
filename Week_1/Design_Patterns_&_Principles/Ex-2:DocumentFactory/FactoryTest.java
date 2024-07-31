/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.factorymethodpattern;

/**
 *
 * @author dell
 */
public class FactoryTest {
    public static void main(String[] args) {
        DocumentFactory wordFactory = new WordFactory();
        DocumentFactory pdfFactory = new PDFFactory();
        DocumentFactory excelFactory = new ExcelFactory();

        Document wordDocument = wordFactory.createDocument();
        Document pdfDocument = pdfFactory.createDocument();
        Document excelDocument = excelFactory.createDocument();

        wordDocument.open();
        wordDocument.save();
        wordDocument.close();
        
        pdfDocument.open();
        pdfDocument.save();
        pdfDocument.close();
        
        excelDocument.open();
        excelDocument.save();
        excelDocument.close();
    }
}

