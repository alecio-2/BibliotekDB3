package com.example.bibliotekdb3;

public class AnstalldAnvandare implements AnvandareMessage {

    private String message;

    @Override
    public void printMessage(String fNamn) {
        message = "Welcome " + fNamn + ", you are logged in as anställd.";
        System.out.println(message);
    }

    public String getMessage() {
        return message;
    }

}
