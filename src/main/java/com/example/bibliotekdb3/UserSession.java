package com.example.bibliotekdb3;

public class UserSession {

    private static String currentUser;

    private static LoginController controller;

    public static String getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(String user) {
        currentUser = user;
    }

    public static LoginController getController() {
        return controller;
    }

    public static void setController(LoginController loginController) {
        controller = loginController;
    }
}