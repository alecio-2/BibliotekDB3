package com.example.bibliotekdb3;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class AddController extends BaseController {
    private Connection conn = DatabaseConnector.getConnection();

    @FXML
    private Button addButton;

    @FXML
    private TextField artikelGenre;

    @FXML
    private TextField artikelKategori;

    @FXML
    private TextField artikelNr;

    @FXML
    private TextField artist;

    @FXML
    private TextField isbn;

    @FXML
    private TextField sab;

    @FXML
    private TextField statusTyp;

    @FXML
    private TextField titel;

    @FXML
    private TextField utgava;

    @FXML
    private void add() throws IOException, SQLException {
        // Ask the user for confirmation

        Optional<ButtonType> result = BaseController.showConfirmation(Alert.AlertType.CONFIRMATION, "Confirmation", "Are you sure you want to add the new row?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User confirmed, add the new row to the table and database

            // Prepare the SQL statement
            String sql = "INSERT INTO Artikel (sab, titel, artist, utgava, artikelGenre, artikelKategori, isbn) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            //Go back to account page
            App.setRoot("account.fxml");


            PreparedStatement stmt = conn.prepareStatement(sql);
            try {
                // Set the parameter values
                stmt.setString(1, sab.getText());
                stmt.setString(2, titel.getText());
                stmt.setString(3, artist.getText());
                stmt.setString(4, utgava.getText());
                stmt.setString(5, artikelGenre.getText());
                stmt.setString(6, artikelKategori.getText());
                stmt.setString(7, isbn.getText());

                // Execute the SQL statement
                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {

                    BaseController.showAlert(Alert.AlertType.INFORMATION, "Information", "A new row has been added to the table.");
                } else {
                    BaseController.showAlert(Alert.AlertType.INFORMATION, "Error", "No new rows have been added to the table.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
