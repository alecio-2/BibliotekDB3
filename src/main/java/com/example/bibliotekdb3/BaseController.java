package com.example.bibliotekdb3;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;

public class BaseController {

    public void backAccount() throws IOException {
        App.setRoot("account.fxml");
    }

    public void back() throws IOException {
        App.setRoot("startPage.fxml");
    }

    public void close() {
        System.exit(0);
    }

    public static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static Optional<ButtonType> showConfirmation(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        return result;
    }
}