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


        /*
       //Print on the console to check if the right information is passed
        System.out.println("artikelNr: " + artikelNr);
        System.out.println("sab: " + sab);
        System.out.println("titel: " + titel);
        System.out.println("artist: " + artist);
        System.out.println("utgava: " + utgava);
        System.out.println("artikelGenre: " + artikelGenre);
        System.out.println("artikelKategori: " + artikelKategori);
        System.out.println("isbn: " + isbn);
        System.out.println("statusTyp: " + statusTyp);
        */

        /*
        //Pass the information from the table to the Labels
        artikelNrLabel.setText(artikelNr);
        sabLabel.setText(sab);
        titelLabel.setText(titel);
        artistLabel.setText(artist);
        utgavaLabel.setText(utgava);
        artikelGenreLabel.setText(artikelGenre);
        artikelKategoriLabel.setText(artikelKategori);
        isbnLabel.setText(isbn);
        statusTypLabel.setText(statusTyp);
        */


    }

    @FXML
    public void save() throws IOException {
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Save confirmation");
        alert.setContentText("Are you sure you want to save the changes?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User confirmed, update the row from the table and database
            String updateQuery = "UPDATE artikel SET sab = ?, titel = ?, artist = ?, utgava = ?, " + "artikelGenre = ?, artikelKategori = ?, isbn = ?, statusTyp = ? " + "WHERE artikelNr = ?";

            //Go back to account page
            App.setRoot("account.fxml");


      /*  // Perform the update operation with the retrieved data
        String updateQuery = "UPDATE artikel SET sab = ?, titel = ?, artist = ?, utgava = ?, " +
                "artikelGenre = ?, artikelKategori = ?, isbn = ?, statusTyp = ? " +
                "WHERE artikelNr = ?"; */

            // Execute the update query using your database connection
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

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Update successful!");
                } else {
                    System.out.println("No rows updated!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        /*
        //Print on the console to check if the right information is passed
        System.out.println("After Update it will be:");
        System.out.println("artikelNr: " + artikelNr);
        System.out.println("sab: " + sab);
        System.out.println("titel: " + titel);
        System.out.println("artist: " + artist);
        System.out.println("utgava: " + utgava);
        System.out.println("artikelGenre: " + artikelGenre);
        System.out.println("artikelKategori: " + artikelKategori);
        System.out.println("isbn: " + isbn);
        System.out.println("statusTyp: " + statusTyp);
        */

        }

    }



}

