package com.example.bibliotekdb3;

public class LarareAnvandare implements AnvandareMessage {
    private String message;

    // Implementation of the printMessage method from AnvandareMessage interface
    @Override
    public void printMessage(String fNamn) {
        // Construct the welcome message for a teacher user
        message = "Welcome " + fNamn + ", you are logged in as teacher.";
        System.out.println(message);
    }

    // Getter method to retrieve the user message
    public String getMessage() {
        return message;
    }
}
