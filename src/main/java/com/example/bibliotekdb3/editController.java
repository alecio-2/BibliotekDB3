package com.example.bibliotekdb3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.io.IOException;

public class editController {

    @FXML
    private TextField artikelGenre;

    @FXML
    private TextField artikelKategori;

    @FXML
    private TextField artikelNr;

    @FXML
    private TextField artist;

    @FXML
    private MenuItem back;

    @FXML
    private MenuItem backAccount;

    @FXML
    private MenuItem close;

    @FXML
    private TextField isbn;

    @FXML
    private TextField sab;

    @FXML
    private Button saveButton;

    @FXML
    private TextField statusTyp;

    @FXML
    private TextField titel;

    @FXML
    private TextField utgava;

    @FXML
    public void save() {



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
