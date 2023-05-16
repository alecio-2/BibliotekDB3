package com.example.bibliotekdb3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController extends BaseController {
    private Connection conn = DatabaseConnector.getConnection();

    private static String loggedInUser = null;

    @FXML
    private TextField usernameInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Button loginButton;

    @FXML
    public void login() throws IOException, SQLException {
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        String fName = null;

        // Create the SQL statement to retrieve user info
        String sql = "SELECT * FROM anvandare WHERE anvandareNr = ? AND losenord = ?";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Create a prepared statement with the SQL statement
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            // Execute the query
            rs = stmt.executeQuery();

            // Check if the query returned any rows
            if (rs.next()) {
                // User is authenticated
                fName = rs.getString("fNamn");

                loggedInUser = username; //Mazkin
                UserSession.setCurrentUser(loggedInUser);

              //  System.out.println("Welcome " + fName);
                App.setRoot("account.fxml");
                System.out.println("Login successful");
            } else {
                // User is not authenticated
                System.out.println("Error: Invalid username or password");
                BaseController.showAlert(Alert.AlertType.ERROR, "Error", "Invalid username or password");
            }
        } finally {
            // Close the result set and prepared statement
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public static String getLoggedInUser() {
        try {
        return loggedInUser;
        } catch (Exception e) {
            System.out.println("Error in getLoggedInUser() from LoginController class: " + e.getMessage());
            return null;
        }
    }



}
