package com.example.bibliotekdb3;

public class BibliotekarieAnvandare implements AnvandareMessage {
    private String message;

    // Implementation of the printMessage method from AnvandareMessage interface
    @Override
    public void printMessage(String fNamn) {
        // Construct the welcome message for a librarian user
        message = "Welcome " + fNamn + ", you are logged in as librarian.";
        System.out.println(message);
    }

    // Getter method to retrieve the user message
    public String getMessage() {
        return message;
    }
}
