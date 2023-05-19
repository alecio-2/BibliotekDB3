package com.example.bibliotekdb3;

public class UserSession {

    // Declare a static variable to hold the current user
    private static String currentUser;

    // Declare a static variable to hold the current controller
    private static LoginController controller;

    // Declare a static method to get the current user
    public static String getCurrentUser() {
        return currentUser;
    }

    // Declare a static method to set the current user
    public static void setCurrentUser(String user) {
        currentUser = user;
    }

    // Declare a static method to get the current controller
    public static LoginController getController() {
        return controller;
    }

    // Declare a static method to set the current controller
    public static void setController(LoginController loginController) {
        controller = loginController;
    }
}