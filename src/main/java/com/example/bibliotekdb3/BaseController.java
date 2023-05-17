package com.example.bibliotekdb3;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;

public class BaseController {

    public void backAccount() throws IOException {
        try {
            App.setRoot("account.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in backAccount() from BaseController class: " + e.getMessage());
        }
    }

    public void backHome() throws IOException {
        try {
            App.setRoot("startPage.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in back() from BaseController class: " + e.getMessage());
        }
    }

    public void close() {
        try {
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in close() from BaseController class: " + e.getMessage());
        }
    }

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