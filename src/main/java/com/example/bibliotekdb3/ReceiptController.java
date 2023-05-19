package com.example.bibliotekdb3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ReceiptController {

    @FXML
    private TextField anvandareNr;

    @FXML
    private TextField ArtikelGenre;

    @FXML
    private TextField ArtikelKategori;

    @FXML
    private TextField Artist;

    @FXML
    private TextField ForfalloDatum;

    @FXML
    private TextField ISBN;

    @FXML
    private TextField LaneDatum;

    @FXML
    private Button OkButton;

    @FXML
    private TextField Utgava;

    @FXML
    private TextField artikelNr;

    @FXML
    private TextField sab;

    @FXML
    private TextField titel;


    public void setReceiptData(String anvandareNr, String artikelGenre, String artikelKategori, String artist,
                               String isbn, String utgava, String artikelNr, String sab, String titel,
                               String LaneDatum, String ForfalloDatum) {
        try {
            // Set the values from the receipt
            this.anvandareNr.setText(anvandareNr);
            this.ArtikelGenre.setText(artikelGenre);
            this.ArtikelKategori.setText(artikelKategori);
            this.Artist.setText(artist);
            this.ISBN.setText(isbn);
            this.Utgava.setText(utgava);
            this.artikelNr.setText(artikelNr);
            this.sab.setText(sab);
            this.titel.setText(titel);
            this.LaneDatum.setText(LaneDatum);
            this.ForfalloDatum.setText(ForfalloDatum);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data in ReceiptController at setReceiptData()" + e);
        }
    }



    public void initialize() {
        try {
            // Clear the receipt data
            anvandareNr.setText("");
            artikelNr.setText("");
            sab.setText("");
            titel.setText("");
            Artist.setText("");
            Utgava.setText("");
            ArtikelGenre.setText("");
            ArtikelKategori.setText("");
            ISBN.setText("");
            LaneDatum.setText("");
            ForfalloDatum.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data in ReceiptController at initialize()" + e);
        }
    }

    @FXML
    public void handleOkButton(ActionEvent actionEvent) {
        // Code to handle the OkButton click event
        Stage stage = (Stage) OkButton.getScene().getWindow();
        stage.close();
    }

}
