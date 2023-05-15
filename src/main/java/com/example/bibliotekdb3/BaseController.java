package com.example.bibliotekdb3;

import java.io.IOException;
import java.sql.Connection;

public class BaseController  {

    public void backAccount() throws IOException {
        App.setRoot("account.fxml");
    }

    public void back() throws IOException {
        App.setRoot("startPage.fxml");
    }

    public void close() {
        System.exit(0);
    }

}