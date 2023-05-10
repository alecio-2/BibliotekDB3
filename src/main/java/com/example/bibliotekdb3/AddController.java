package com.example.bibliotekdb3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddController {
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
    private void add() {
        try {
            // Prepare the SQL statement
            String sql = "INSERT INTO Artikel (sab, titel, artist, utgava, artikelGenre, artikelKategori, isbn) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

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
                System.out.println("A new row has been added to the table.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }







    @FXML
    public void backAccount() throws IOException {
        App.setRoot("account.fxml");
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
