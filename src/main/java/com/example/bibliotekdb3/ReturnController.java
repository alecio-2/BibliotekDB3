package com.example.bibliotekdb3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.jar.JarEntry;

public class ReturnController extends BaseController {

    private Connection conn = DatabaseConnector.getConnection();

    String currentUser;

    @FXML
    private TextField inputField;

    @FXML
    private Button returnButton;

    public void returnArticle() throws SQLException {
        String input = inputField.getText();

        // Ask the user for confirmation
        Optional<ButtonType> result = BaseController.showConfirmation(Alert.AlertType.CONFIRMATION, "Confirmation", "Are you sure you want to return the object with article no: " + input + "?");

        if (result.isPresent() && result.get() == ButtonType.OK) {

            // User confirmed, add the new row to the table and database
            String currentUser = UserSession.getCurrentUser();

            System.out.println("Return made by user no: " + currentUser);
            System.out.println("Returned the object no: " + inputField.getText());

            //String input = inputField.getText();
            String sql1 = "SELECT lan.lanNr FROM lan JOIN lanartikel ON lan.lanNr = lanartikel.lanNr WHERE lan.anvandareNr = ? and lanartikel.artikelNr = ?;";

            PreparedStatement stmt1 = conn.prepareStatement(sql1);

            try {

                stmt1.setString(1, currentUser);
                stmt1.setString(2, input);
                ResultSet rs = stmt1.executeQuery();

                //write the result of the query to a variable currentLanNr
                if (rs.next()) {
                    // Retrieve the value of lanNr from the result set
                    String currentLanNr = rs.getString("lanNr");

                    // Print the value to the console to test the function of getthing the right lanNr
                    // System.out.println("Current Lan Nr: " + currentLanNr);

                    // Insert the new row into the database
                    String sql = "INSERT INTO inlamningsdatum (lanNr, artikelNr, inlamningsDatum) VALUES (?, ?, ?)";

                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, currentLanNr);
                    stmt.setString(2, input);
                    stmt.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
                    stmt.executeUpdate();

                    // Return to the account page
                    App.setRoot("account.fxml");

                    BaseController.showAlert(Alert.AlertType.INFORMATION, "Information", "Object returned successfully.");
                } else {
                    BaseController.showAlert(Alert.AlertType.INFORMATION, "Error", "No active loan no found for the current user on that object.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error in returnArticle() in ReturnController class : " + e.getMessage());
            }
        }
    }


}
