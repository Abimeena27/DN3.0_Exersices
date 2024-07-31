/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.decoratorpattern;

/**
 *
 * @author dell
 */
public class DecoratorMain {
    public static void main(String[] args) {
        // Basic email notifier
        Notifier emailNotifier = new EmailNotifier();
        emailNotifier.send("Hello World!");

        // Email notifier with SMS
        Notifier smsNotifier = new SMSNotifier(emailNotifier);
        smsNotifier.send("Hello World!");

        // Email notifier with SMS and Slack
        Notifier slackNotifier = new SlackNotifier(smsNotifier);
        slackNotifier.send("Hello World!");
    }
}

