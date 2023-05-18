package com.example.bibliotekdb3;

public class ForskareAnvandare implements AnvandareMessage {
    @Override
    public void printMessage(String fNamn) {
        System.out.println("Welcome, you are a forskare.");
    }
}
