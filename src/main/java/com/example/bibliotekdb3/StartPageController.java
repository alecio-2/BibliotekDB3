package com.example.bibliotekdb3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;


import java.io.IOException;
import java.sql.Connection;

public class StartPageController extends BaseController {

    private Connection conn = DatabaseConnector.getConnection();

    @FXML
    private Button loginButtonSP;

    @FXML
    private Button searchButtonSP;

    public void loginSP() throws IOException {
        try {
            App.setRoot("login.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in loginSP() from StartPageController class: " + e.getMessage());
        }
    }

    public void searchSP() throws IOException {
        try {
            App.setRoot("searchWOlogin.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in searchSP() from StartPageController class: " + e.getMessage());
        }
    }


}
