package com.example.bibliotekdb3;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;

public class LateController extends BaseController {

    private Connection conn = DatabaseConnector.getConnection();

    @FXML
    private Button populateButton;

    @FXML
    private TableView searchResults;

    @FXML
    public void populate() {


        try {
            // Create prepared statement
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT lanartikel.artikelNr, lan.lanNr,artikel.titel, artikel.artist, " +
                            "anvandare.anvandareNr, anvandare.fNamn, anvandare.eNamn, " +
                            "anvandare.email, lanartikel.laneDatum, lanartikel.forfalloDatum " +
                            "FROM lanartikel " +
                            "JOIN lan ON lanartikel.lanNr = lan.lanNr " +
                            "JOIN artikel ON lanartikel.artikelNr = artikel.artikelNr " +
                            "JOIN anvandare ON lan.anvandareNr = anvandare.anvandareNr " +
                            "WHERE lanartikel.forfalloDatum < CURDATE()");

            // Execute prepared statement
            ResultSet rs = stmt.executeQuery();

            // Create table columns based on metadata of result set
            searchResults.getColumns().clear(); // Remove existing columns
            ResultSetMetaData rsmd = rs.getMetaData();

            int numCols = rsmd.getColumnCount();
            for (int i = 0; i < numCols; i++) {
                final int colIdx = i;
                TableColumn<ObservableList<String>, String> column = new TableColumn<>(rsmd.getColumnName(i + 1));
                column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(colIdx)));
                searchResults.getColumns().add(column);
            }

            // Add data to table
            ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= numCols; i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            // Populate table
            searchResults.setItems(data);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error on populating the table with late loans: " + e);
        }
    }
}
