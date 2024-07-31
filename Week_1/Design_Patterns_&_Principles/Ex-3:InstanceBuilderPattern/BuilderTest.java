/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.instancebuilder;

/**
 *
 * @author dell
 */
public class BuilderTest {
    public static void main(String[] args) {
        // Creating a basic computer with only required attributes
        Computer basicComputer = new Computer.Builder("Intel i5", "8GB").build();
        System.out.println(basicComputer);

        // Creating an advanced computer with optional attributes
        Computer advancedComputer = new Computer.Builder("Intel i7", "16GB")
                .setStorage("512GB SSD")
                .setGraphicsCard("NVIDIA GTX 1660")
                .setOperatingSystem("Windows 10")
                .build();
        System.out.println(advancedComputer);

        // Creating a gaming computer with different configuration
        Computer gamingComputer = new Computer.Builder("AMD Ryzen 9", "32GB")
                .setStorage("1TB SSD")
                .setGraphicsCard("NVIDIA RTX 3080")
                .setOperatingSystem("Windows 11")
                .build();
        System.out.println(gamingComputer);
    }
}
