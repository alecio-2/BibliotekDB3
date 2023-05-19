package com.example.bibliotekdb3;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;


public class BaseController {

    // Navigates to the account view
    public void backAccount() throws IOException {
        try {
            App.setRoot("account.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in backAccount() from BaseController class: " + e.getMessage());
        }
    }

    // Navigates to the home/start page view
    public void backHome() throws IOException {
        try {
            App.setRoot("startPage.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in back() from BaseController class: " + e.getMessage());
        }
    }

    // Closes the application
    public void close() {
        try {
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in close() from BaseController class: " + e.getMessage());
        }
    }

    // Displays an alert dialog with the specified alert type, title, and message
    public static void showAlert(Alert.AlertType alertType, String title, String message) {
        try {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in showAlert() from BaseController class: " + e.getMessage());
        }
    }

    // Displays a confirmation dialog with the specified alert type, title, and message
    public static Optional<ButtonType> showConfirmation(Alert.AlertType alertType, String title, String message) {
        try {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            Optional<ButtonType> result = alert.showAndWait();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in showConfirmation() from BaseController class: " + e.getMessage());
            return Optional.empty();
        }
    }

}

