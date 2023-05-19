package com.example.bibliotekdb3;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class EditController extends BaseController {
    private Connection conn = DatabaseConnector.getConnection();

    String currentUser; //

    public Button saveButton;
    public TextField sabField;
    public TextField statusTypField;
    public Label statusTypLabel;
    public Label isbnLabel;
    public Label artikelKategoriLabel;
    public Label artikelGenreLabel;
    public Label utgavaLabel;
    public Label artistLabel;
    public Label sabLabel;
    public Label artikelNrLabel;


    @FXML
    private Label titelLabel;

    @FXML
    private TextField artikelNrField;


    @FXML
    private TextField titelField;


    @FXML
    private TextField artistField;


    @FXML
    private TextField utgavaField;


    @FXML
    private TextField artikelGenreField;


    @FXML
    private TextField artikelKategoriField;


    @FXML
    private TextField isbnField;


    //Method to get the data from the selected row in the table and pass it to the TextFields
    public void receiveDetails(String artikelNr, String sab, String titel, String artist, String utgava, String artikelGenre, String artikelKategori, String isbn, String statusTyp) {

        try {
            //Pass the information from the table to the TextFields
            artikelNrField.setText(artikelNr);
            sabField.setText(sab);
            titelField.setText(titel);
            artistField.setText(artist);
            utgavaField.setText(utgava);
            artikelGenreField.setText(artikelGenre);
            artikelKategoriField.setText(artikelKategori);
            isbnField.setText(isbn);
            statusTypField.setText(statusTyp);
        } catch (Exception e) {
            System.out.println("Error in receiveDetails() from EditController class: " + e.getMessage());
            e.printStackTrace();
        }

    }

    @FXML
    public void save() throws IOException {

        // Get the data from the TextFields
        String artikelNr = artikelNrField.getText();
        String sab = sabField.getText();
        String titel = titelField.getText();
        String artist = artistField.getText();
        String utgava = utgavaField.getText();
        String artikelGenre = artikelGenreField.getText();
        String artikelKategori = artikelKategoriField.getText();
        String isbn = isbnField.getText();
        String statusTyp = statusTypField.getText();

        // Ask the user for confirmation
        Optional<ButtonType> result = BaseController.showConfirmation(Alert.AlertType.CONFIRMATION, "Save confirmation", "Are you sure you want to save the changes?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User confirmed, update the row from the table and database
            String updateQuery = "UPDATE artikel SET sab = ?, titel = ?, artist = ?, utgava = ?, artikelGenre = ?, artikelKategori = ?, isbn = ?, statusTyp = ?  WHERE artikelNr = ?";

            //Go back to account page
            App.setRoot("account.fxml");


            // Execute the update query using database connection
            try {
                PreparedStatement statement = conn.prepareStatement(updateQuery);
                statement.setString(1, sab);
                statement.setString(2, titel);
                statement.setString(3, artist);
                statement.setString(4, utgava);
                statement.setString(5, artikelGenre);
                statement.setString(6, artikelKategori);
                statement.setString(7, isbn);
                statement.setString(8, statusTyp);
                statement.setString(9, artikelNr);

                // Execute the update query
                int rowsAffected = statement.executeUpdate();

                // Check if the update was successful
                if (rowsAffected > 0) {
                    BaseController.showAlert(Alert.AlertType.INFORMATION, "Update successful!", "The changes have been saved.");
                } else {
                    BaseController.showAlert(Alert.AlertType.ERROR, "Update failed!", "The changes could not be saved.");
                }
            } catch (SQLException e) {
                BaseController.showAlert(Alert.AlertType.ERROR, "Database error!", "An error occurred while trying to update the row: " + e.getMessage());
                e.printStackTrace();
            }


        }

    }


}

