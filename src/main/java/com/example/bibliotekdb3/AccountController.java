package com.example.bibliotekdb3;

import com.example.bibliotekdb3.EditController;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;

import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class AccountController extends BaseController {

    private Connection conn = DatabaseConnector.getConnection();

    String currentUser;
    @FXML
    private Button lateButton;

    @FXML
    private Button add;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private MenuItem back;

    @FXML
    private MenuItem close;

    @FXML
    private TableView results;

    @FXML
    private Button showBorrows;

    @FXML
    private Button borrow;

    @FXML
    private Button showReserved;

    @FXML
    private TextField searchField;

    @FXML
    public void showBorrows() {
        // Get the current user
        String currentUser = UserSession.getCurrentUser();  //Mazkin
        System.out.println("Current user: " + currentUser);   //Mazkin

        if (currentUser != null) { //Mazkin

            String anvandareNr = currentUser; //Mazkin
            try {
              //  PreparedStatement stmt = conn.prepareStatement("SELECT a.fNamn, l.lanNr, ar.artikelNr, la.laneDatum, la.forfalloDatum, " + "ar.titel, ar.artist, ar.isbn " + "FROM anvandare a " + "JOIN lan l ON a.anvandareNr = l.anvandareNr " + "JOIN lanartikel la ON l.lanNr = la.lanNr " + "JOIN artikel ar ON la.artikelNr = ar.artikelNr " + "WHERE a.anvandareNr = ?");
                //PreparedStatement stmt = conn.prepareStatement("SELECT a.fNamn, l.lanNr, ar.artikelNr, la.laneDatum, la.forfalloDatum, ar.titel, ar.artist, ar.isbn FROM anvandare a JOIN lan l ON a.anvandareNr = l.anvandareNr JOIN lanartikel la ON l.lanNr = la.lanNr JOIN artikel ar ON la.artikelNr = ar.artikelNr WHERE a.anvandareNr = ?");
                PreparedStatement stmt = conn.prepareStatement("SELECT anvandare.fNamn, lan.lanNr, artikel.artikelNr, lanartikel.laneDatum, lanartikel.forfalloDatum, artikel.titel, artikel.artist, artikel.isbn FROM anvandare JOIN lan  ON anvandare.anvandareNr = lan.anvandareNr JOIN lanartikel ON lan.lanNr = lanartikel.lanNr JOIN artikel ON lanartikel.artikelNr = artikel.artikelNr WHERE lan.anvandareNr = ? AND lanartikel.lanNr NOT IN (SELECT lanNr FROM inlamningsdatum)");
                stmt.setInt(1, Integer.parseInt(anvandareNr));
                ResultSet rs = stmt.executeQuery();

                // Create table columns based on metadata of result set
                results.getColumns().clear(); // Remove existing columns
                ResultSetMetaData rsmd = rs.getMetaData();
                int numCols = rsmd.getColumnCount();
                for (int i = 0; i < numCols; i++) {
                    final int colIdx = i;
                    TableColumn<ObservableList<String>, String> column = new TableColumn<>(rsmd.getColumnName(i + 1));
                    column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(colIdx)));
                    results.getColumns().add(column);
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
                results.setItems(data);

            } catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("An error occurred while executing the query.");
                alert.showAndWait();
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid anvandareNr.");
                alert.showAndWait();
            }
        }
        ;
    }

    @FXML
    public void showReserved() {
        // Get the current user
        String currentUser = UserSession.getCurrentUser();  //Mazkin
        System.out.println("Current user: " + currentUser);   //Mazkin

        if (currentUser != null) { //Mazkin

            String anvandareNr = currentUser; //Mazkin
            try {
                PreparedStatement stmt = conn.prepareStatement("SELECT a.fNamn, re.reservationNr, ar.artikelNr, re.reservationDatum, ar.titel, ar.artist, ar.isbn " + "FROM reservation re  " + "JOIN anvandare a ON a.anvandareNr = re.anvandareNr " + "JOIN artikel ar ON ar.artikelNr = re.artikelNr " + "WHERE a.anvandareNr = ?");
                stmt.setInt(1, Integer.parseInt(anvandareNr));
                ResultSet rs = stmt.executeQuery();

                // Create table columns based on metadata of result set
                results.getColumns().clear(); // Remove existing columns
                ResultSetMetaData rsmd = rs.getMetaData();
                int numCols = rsmd.getColumnCount();
                for (int i = 0; i < numCols; i++) {
                    final int colIdx = i;
                    TableColumn<ObservableList<String>, String> column = new TableColumn<>(rsmd.getColumnName(i + 1));
                    column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(colIdx)));
                    results.getColumns().add(column);
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
                results.setItems(data);

            } catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("An error occurred while executing the query.");
                alert.showAndWait();
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid anvandareNr.");
                alert.showAndWait();
            }
        }
        ;
    }

    @FXML
    public void initialize() {
        String currentUser = UserSession.getCurrentUser();
        if (currentUser != null) {
            String anvandareNr = currentUser;

            try {
                PreparedStatement stmt = conn.prepareStatement("SELECT anstalldTyp FROM anstalld WHERE anvandareNr = ?");
                stmt.setInt(1, Integer.parseInt(anvandareNr));
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String anvandareTyp = rs.getString("anstalldTyp");
                    // Show/hide buttons based on anstalldTyp
                    // Here we can develop furture funcitons based on the user type
                    if (anvandareTyp != null && !"Bibliotekarie".equals(anvandareTyp)) {
                        add.setVisible(false);
                        editButton.setVisible(false);
                        deleteButton.setVisible(false);
                        lateButton.setVisible(false);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the exception
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Handle the exception
            }
        }
    }

    @FXML
    public void returnArticle() throws IOException {
        App.setRoot("return.fxml");
    }

    @FXML
    public void late() throws IOException {
        App.setRoot("late.fxml");
    }

    @FXML
    public void add() throws IOException {
        App.setRoot("add.fxml");
    }

    @FXML
    public void edit() throws IOException {

        // Get the selected row
        ObservableList<String> selectedRow = (ObservableList<String>) results.getSelectionModel().getSelectedItem();
        if (selectedRow == null) {
            // No row selected, do nothing
            return;
        } else {


            String artikelNr = selectedRow.get(0); //artikelNr
            String sab = selectedRow.get(1); // sab
            String titel = selectedRow.get(2); // titel
            String artist = selectedRow.get(3); // artist
            String utgava = selectedRow.get(4); // utgava
            String artikelGenre = selectedRow.get(5); // artikelGenre
            String artikelKategori = selectedRow.get(6); // artikelKategori
            String isbn = selectedRow.get(7); // ISBN
            String statusTyp = selectedRow.get(8); // statusTyp

            // Open the edit window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("edit.fxml"));
            Parent editRoot = loader.load();

            // Pass the data to the controller
            EditController editController = loader.getController();
            editController.receiveDetails(artikelNr, sab, titel, artist, utgava, artikelGenre, artikelKategori, isbn, statusTyp);

            /*// Show the window
            Stage stage = new Stage();
            stage.setScene(new Scene(editRoot));
            stage.show();*/

            // Replace the content of the current window with edit.fxml content
            Scene scene = results.getScene();
            scene.setRoot(editRoot);
        }
    }

    @FXML
    public void delete() {
        // Get the selected row
        ObservableList<String> selectedRow = (ObservableList<String>) results.getSelectionModel().getSelectedItem();
        if (selectedRow == null) {
            // No row selected, do nothing
            return;
        }

        // Get the title of the book to be deleted
        String title = selectedRow.get(2); // assuming the title is in the third column

        // Ask the user for confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Delete Book");
        alert.setContentText("Are you sure you want to delete \"" + title + "\"?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User confirmed, delete the row from the table and database
            try {
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM artikel WHERE artikelNr = ?");
                stmt.setInt(1, Integer.parseInt(selectedRow.get(0))); // assuming the article number is in the first column
                stmt.executeUpdate();
                results.getItems().remove(selectedRow);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void searchDB() {

        String searchStr = searchField.getText();

        try {

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM artikel WHERE artikelNr LIKE ? OR titel LIKE ? OR artist LIKE ? OR utgava LIKE ? OR artikelGenre LIKE ? OR artikelKategori LIKE ? OR isbn LIKE ?");
            stmt.setString(1, "%" + searchStr + "%");
            stmt.setString(2, "%" + searchStr + "%");
            stmt.setString(3, "%" + searchStr + "%");
            stmt.setString(4, "%" + searchStr + "%");
            stmt.setString(5, "%" + searchStr + "%");
            stmt.setString(6, "%" + searchStr + "%");
            stmt.setString(7, "%" + searchStr + "%");
            ResultSet rs = stmt.executeQuery();
            // Create table columns based on metadata of result set
            results.getColumns().clear(); // Remove existing columns
            ResultSetMetaData rsmd = rs.getMetaData();
            int numCols = rsmd.getColumnCount();
            for (int i = 0; i < numCols; i++) {
                final int colIdx = i;
                TableColumn<ObservableList<String>, String> column = new TableColumn<>(rsmd.getColumnName(i + 1));
                column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(colIdx)));
                results.getColumns().add(column);
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
            results.setItems(data);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void borrow() {
        // Get the current user
        String currentUser = UserSession.getCurrentUser();
        System.out.println("Current user: " + currentUser);

        ObservableList<String> selectedItem = (ObservableList<String>) results.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String artikelNr = selectedItem.get(0);


            if (currentUser != null) {

                String anvandareNr = currentUser;
                try {
                    // Insert into lan table
                    PreparedStatement stmt1 = conn.prepareStatement("INSERT INTO lan (anvandareNr) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                    stmt1.setString(1, anvandareNr);
                    stmt1.executeUpdate();

                    // Get the lanNr generated by the previous insert
                    ResultSet rs = stmt1.getGeneratedKeys();
                    rs.next();
                    int lanNr = rs.getInt(1);

                    // Insert into lanartikel table
                    PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO lanartikel (lanNr, artikelNr) VALUES (?, ?)");
                    stmt2.setInt(1, lanNr);
                    stmt2.setString(2, artikelNr);
                    stmt2.executeUpdate();

                    System.out.println("Successfully borrowed item with artikelNr: " + artikelNr + " to anvandareNr: " + anvandareNr);
                } catch (SQLException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Unable to borrow item");
                    alert.setContentText("An error occurred while borrowing the item.");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }

       /*     String sab = selectedItem.get(1);
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
*/

            }
        } else {
            //No item is selected, show an error message to the user
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select an item to borrow.");
            alert.showAndWait();
        }
    }


}

//Unused code

    /*  @FXML
     private TableView<?> results;*/
//search method normal
   /* @FXML
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
            results.getColumns().clear(); // Remove existing columns
            ResultSetMetaData rsmd = rs.getMetaData();
            int numCols = rsmd.getColumnCount();
            for (int i = 0; i < numCols; i++) {
                final int colIdx = i;
                TableColumn<ObservableList<String>, String> column = new TableColumn<>(rsmd.getColumnName(i + 1));
                column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(colIdx)));
                results.getColumns().add(column);
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
            results.setItems(data);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

//edit method with editable row and delete button on each row
   /* public void edit() {

        String searchStr = searchField.getText();

        try {

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM artikel WHERE artikelNr LIKE ? OR titel LIKE ? OR artist LIKE ? OR utgava LIKE ? OR artikelGenre LIKE ? OR artikelKategori LIKE ? OR isbn LIKE ?");
            stmt.setString(1, "%" + searchStr + "%");
            stmt.setString(2, "%" + searchStr + "%");
            stmt.setString(3, "%" + searchStr + "%");
            stmt.setString(4, "%" + searchStr + "%");
            stmt.setString(5, "%" + searchStr + "%");
            stmt.setString(6, "%" + searchStr + "%");
            stmt.setString(7, "%" + searchStr + "%");
            ResultSet rs = stmt.executeQuery();

            // Create table columns based on metadata of result set
            results.getColumns().clear(); // Remove existing columns
            ResultSetMetaData rsmd = rs.getMetaData();
            int numCols = rsmd.getColumnCount();
            for (int i = 0; i < numCols; i++) {
                final int colIdx = i;

                TableColumn<ObservableList<String>, String> column = new TableColumn<>(rsmd.getColumnName(i + 1));
                column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(colIdx)));

                if (i != 0 && i != numCols - 1) { // Make all columns editable except the first and last
                    column.setCellFactory(TextFieldTableCell.forTableColumn());
                    column.setOnEditCommit(event -> {

                        // Update the value in the table
                        TablePosition<ObservableList<String>, String> pos = event.getTablePosition();
                        ObservableList<String> row = pos.getTableView().getItems().get(pos.getRow());
                        row.set(colIdx, event.getNewValue());

                        // Update the value in the database
                        try {
                            PreparedStatement updateStmt = conn.prepareStatement("UPDATE artikel SET " + rsmd.getColumnName(colIdx + 1) + " = ? WHERE artikelNr = ?");
                            updateStmt.setString(1, event.getNewValue());
                            updateStmt.setInt(2, Integer.parseInt(row.get(0)));
                            updateStmt.executeUpdate();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });
                } else if (i == numCols - 1) { // Add delete button to last column
                    TableColumn<ObservableList<String>, Void> deleteColumn = new TableColumn<>("Delete");
                    deleteColumn.setCellFactory(param -> new TableCell<>() {
                        private final Button deleteButton = new Button("Delete");

                        @Override
                        protected void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                setGraphic(deleteButton);
                            }
                            deleteButton.setOnAction(event -> {
                                // Get the row data and remove it from the table
                                ObservableList<String> rowData = getTableView().getItems().get(getIndex());
                                results.getItems().remove(rowData);

                                // Delete the row from the database
                                try {
                                    PreparedStatement deleteStmt = conn.prepareStatement("DELETE FROM artikel WHERE artikelNr = ?");
                                    deleteStmt.setInt(1, Integer.parseInt(rowData.get(0)));
                                    deleteStmt.executeUpdate();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                    });
                    results.getColumns().add(deleteColumn);
                }
                results.getColumns().add(column);
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
            results.setItems(data);

            // Enable editing
            results.setEditable(false);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

//edit method with editable rows
   /* public void edit() {

        String searchStr = searchField.getText();

        try {

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM artikel WHERE artikelNr LIKE ? OR titel LIKE ? OR artist LIKE ? OR utgava LIKE ? OR artikelGenre LIKE ? OR artikelKategori LIKE ? OR isbn LIKE ?");
            stmt.setString(1, "%" + searchStr + "%");
            stmt.setString(2, "%" + searchStr + "%");
            stmt.setString(3, "%" + searchStr + "%");
            stmt.setString(4, "%" + searchStr + "%");
            stmt.setString(5, "%" + searchStr + "%");
            stmt.setString(6, "%" + searchStr + "%");
            stmt.setString(7, "%" + searchStr + "%");
            ResultSet rs = stmt.executeQuery();

            // Create table columns based on metadata of result set
            results.getColumns().clear(); // Remove existing columns
            ResultSetMetaData rsmd = rs.getMetaData();
            int numCols = rsmd.getColumnCount();
            for (int i = 0; i < numCols; i++) {
                final int colIdx = i;

                TableColumn<ObservableList<String>, String> column = new TableColumn<>(rsmd.getColumnName(i + 1));
                column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(colIdx)));

                if (i != 0 && i != numCols - 1) { // Make all columns editable except the first and last
                    column.setCellFactory(TextFieldTableCell.forTableColumn());
                    column.setOnEditCommit(event -> {

                        // Update the value in the table
                        TablePosition<ObservableList<String>, String> pos = event.getTablePosition();
                        ObservableList<String> row = pos.getTableView().getItems().get(pos.getRow());
                        row.set(colIdx, event.getNewValue());

                        // Update the value in the database
                        try {
                            PreparedStatement updateStmt = conn.prepareStatement("UPDATE artikel SET " + rsmd.getColumnName(colIdx + 1) + " = ? WHERE artikelNr = ?");
                            updateStmt.setString(1, event.getNewValue());
                            updateStmt.setInt(2, Integer.parseInt(row.get(0)));
                            updateStmt.executeUpdate();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });
                }
                results.getColumns().add(column);
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
            results.setItems(data);

            // Enable editing
            results.setEditable(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/






