/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mvc;

/**
 *
 * @author dell
 */
public class Main{
    public static void main(String[] args) {
        Student student = new Student("John Doe", 1, "A");

        S_View view = new S_View();

        S_Contrller controller = new S_Contrller(student, view);

        controller.updateView();

        System.out.println();

        controller.setStudentName("Jane Doe");
        controller.setStudentGrade("B");

        controller.updateView();
    }
}
