package com.example.bibliotekdb3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;


import java.io.IOException;
import java.sql.Connection;

public class StartPageController {
    private Connection conn = DatabaseConnector.getConnection();

    @FXML
    private Button loginButtonSP;
    @FXML
    private Button searchButtonSP;

    public void loginSP() throws IOException {
        App.setRoot("login.fxml");

    }

    public void searchSP() throws IOException {
        App.setRoot("searchWOlogin.fxml");

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
