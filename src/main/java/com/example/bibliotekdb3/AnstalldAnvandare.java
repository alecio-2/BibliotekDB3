package com.example.bibliotekdb3;

public class AnstalldAnvandare implements AnvandareMessage {


    @Override
    public void printMessage(String fNamn) {

        System.out.println("Welcome "+ fNamn +   ", you are logged in as anställd."  );

    }
}