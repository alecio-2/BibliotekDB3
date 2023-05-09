package com.example.bibliotekdb3;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

public class SearchWloginController {
    private Connection conn = DatabaseConnector.getConnection();



    @FXML
    private TextField searchField;

    @FXML
    private TableView searchResults;






    @FXML
    public void searchDB() {

        String searchStr = searchField.getText();

        try {

            PreparedStatement stmt = conn.prepareStatement
                    ("SELECT * FROM artikel WHERE artikelNr LIKE ? OR titel LIKE ? OR artist LIKE ? OR utgava LIKE ? OR artikelGenre LIKE ? OR artikelKategori LIKE ? OR isbn LIKE ?");
            stmt.setString(1, "%" + searchStr + "%");
            stmt.setString(2, "%" + searchStr + "%");
            stmt.setString(3, "%" + searchStr + "%");
            stmt.setString(4, "%" + searchStr + "%");
            stmt.setString(5, "%" + searchStr + "%");
            stmt.setString(6, "%" + searchStr + "%");
            stmt.setString(7, "%" + searchStr + "%");
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
            searchResults.setItems(data);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void borrow() {


        ObservableList<String> selectedItem = (ObservableList<String>) searchResults.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String artikelNr = selectedItem.get(0);
            String sab = selectedItem.get(1);
            String titel = selectedItem.get(2);
            String artist = selectedItem.get(3);
            String utgava = selectedItem.get(4);
            String artikelGenre = selectedItem.get(5);
            String artikelKategori = selectedItem.get(6);
            String isbn = selectedItem.get(7);
            String statusTyp = selectedItem.get(8);

            System.out.println("artikelNr: " + artikelNr);
            System.out.println("sab: " + sab);
            System.out.println("titel: " + titel);
            System.out.println("artist: " + artist);
            System.out.println("utgava: " + utgava);
            System.out.println("artikelGenre: " + artikelGenre);
            System.out.println("artikelKategori: " + artikelKategori);
            System.out.println("isbn: " + isbn);
            System.out.println("statusTyp: " + statusTyp);
            System.out.println("--------------------------");


        }
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
