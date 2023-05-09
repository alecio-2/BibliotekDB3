package com.example.bibliotekdb3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    private Connection conn = DatabaseConnector.getConnection();


    @FXML
    private TextField usernameInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Button loginButton;

    /**
     * Initializes the controller class.
     *
     * @throws IOException
     */

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
                System.out.println("Welcome " + fName);

                App.setRoot("searchWlogin.fxml");
                System.out.println("Login successful");
            } else {
                // User is not authenticated
                System.out.println("Error: Invalid username or password");
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


    @FXML
    public void back() throws IOException {

        App.setRoot("startPage.fxml");

    }

    @FXML
    public void close() {
        System.exit(0);
    }


}
