package com.example.bibliotekdb3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {


        // Load the FXML file
        Parent root = FXMLLoader.load(getClass().getResource("startPage.fxml"));


        // Set the scene to the stage
        scene = new Scene(root, 800, 600); // assign to static scene variable
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {

        // Load the FXML file and set it to the scene
        scene.setRoot(loadFXML(fxml));

    }

    private static Parent loadFXML(String fxml) throws IOException {

        // Load the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));

        // Return the root node
        return fxmlLoader.load();

    }

    public static void main(String[] args) {

        // Launch the application
        launch();

    }

}
