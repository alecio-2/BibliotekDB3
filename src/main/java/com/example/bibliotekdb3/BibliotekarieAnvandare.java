package com.example.bibliotekdb3;

public class BibliotekarieAnvandare implements AnvandareMessage {


    @Override
    public void printMessage(String fNamn) {
        System.out.println("Welcome "+  fNamn +  ", you are logged in as bibliotekarie."  );

    }
}
