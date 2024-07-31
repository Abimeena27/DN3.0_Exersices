/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.commandpattern;

/**
 *
 * @author dell
 */
public class Main {
    public static void main(String[] args) {
        Light light = new Light();
        
        Command lightOn = new LightOn(light);
        Command lightOff = new LightOff(light);
        
        RemoteControl remote = new RemoteControl();
        
        // Turn the light on
        remote.setCommand(lightOn);
        remote.pressButton();
        
        System.out.println();
        
        // Turn the light off
        remote.setCommand(lightOff);
        remote.pressButton();
    }
}

