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
        try {
            // Load the FXML file
            Parent root = FXMLLoader.load(getClass().getResource("startPage.fxml"));
            // Set the scene to the stage
            scene = new Scene(root, 1000, 750); // assign to static scene variable
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in start() from class App: " + e.getMessage());
        }
    }

    static void setRoot(String fxml) throws IOException {
        try {
            // Load the FXML file and set it to the scene
            scene.setRoot(loadFXML(fxml));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in setRoot() from class App: " + e.getMessage());
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        try {
            // Load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
            // Return the root node
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in loadFXML() from class App: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            // Launch the application
            launch();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in main() from class App: " + e.getMessage());
        }
    }

}
