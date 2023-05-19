package com.example.bibliotekdb3;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

public class SearchWOloginController extends BaseController {

    // Get the connection to the database
    private Connection conn = DatabaseConnector.getConnection();

    @FXML
    private TextField searchField;

    @FXML
    private TableView results;

    @FXML
    public void searchDB() {
        // Get the input from the user
        String searchStr = searchField.getText();
        try {
            // Create a prepared statement
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * " +
                            "FROM artikel " +
                            "WHERE artikelNr LIKE ? " +
                            "OR titel LIKE ? " +
                            "OR artist LIKE ? " +
                            "OR utgava LIKE ? " +
                            "OR artikelGenre LIKE ? " +
                            "OR artikelKategori LIKE ? " +
                            "OR isbn LIKE ?");

            // Set the parameters
            stmt.setString(1, "%" + searchStr + "%");
            stmt.setString(2, "%" + searchStr + "%");
            stmt.setString(3, "%" + searchStr + "%");
            stmt.setString(4, "%" + searchStr + "%");
            stmt.setString(5, "%" + searchStr + "%");
            stmt.setString(6, "%" + searchStr + "%");
            stmt.setString(7, "%" + searchStr + "%");
            // Execute the query
            ResultSet rs = stmt.executeQuery();

            // Create table columns based on metadata of result set
            results.getColumns().clear(); // Remove existing columns
            // Get the metadata
            ResultSetMetaData rsmd = rs.getMetaData();
            // Create columns
            int numCols = rsmd.getColumnCount();
            for (int i = 0; i < numCols; i++) {
                final int colIdx = i;
                TableColumn<ObservableList<String>, String> column = new TableColumn<>(rsmd.getColumnName(i + 1));
                column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(colIdx)));
                results.getColumns().add(column);
            }

            // Add data to table
            ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
            // Loop through the result set
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= numCols; i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            // Add the data to the table
            results.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error loading the table: " + e.getMessage());
        }
    }
}

