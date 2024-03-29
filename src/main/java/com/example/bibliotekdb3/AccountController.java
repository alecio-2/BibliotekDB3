package com.example.bibliotekdb3;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    @FXML
    private TextField userMessage;

    @FXML
    private Button lateButton;

    @FXML
    private Button add;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

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
        String currentUser = UserSession.getCurrentUser();

        // Check if the user is logged in
        if (currentUser != null) {

            String anvandareNr = currentUser;
            try {
                // Prepare the SQL statement to retrieve borrow information
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT anvandare.fNamn, lan.lanNr, artikel.artikelNr, " +
                                "lanartikel.laneDatum, lanartikel.forfalloDatum, artikel.titel, " +
                                "artikel.artist, artikel.isbn " +
                                "FROM anvandare " +
                                "JOIN lan  ON anvandare.anvandareNr = lan.anvandareNr " +
                                "JOIN lanartikel ON lan.lanNr = lanartikel.lanNr " +
                                "JOIN artikel ON lanartikel.artikelNr = artikel.artikelNr " +
                                "WHERE lan.anvandareNr = ? " +
                                "AND lanartikel.lanNr NOT IN (SELECT lanNr FROM inlamningsdatum)");

                stmt.setInt(1, Integer.parseInt(anvandareNr));

                // Execute the query
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
                // Display data in table
                results.setItems(data);

            } catch (SQLException e) {
                // Handle the exception
                BaseController.showAlert(Alert.AlertType.ERROR,
                        "Error",
                        "An error occurred while executing the query.: " + e.getMessage());
            } catch (NumberFormatException e) {
                // Handle the exception
                BaseController.showAlert(Alert.AlertType.ERROR,
                        "Error",
                        "Invalid user no: " + e.getMessage());
            }
        }
    }

    @FXML
    public void showReserved() {
        // Get the current user
        String currentUser = UserSession.getCurrentUser();

        // Check if the user is logged in
        if (currentUser != null) {

            String anvandareNr = currentUser;
            try {
                // Prepare the SQL statement to retrieve reservation information
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT a.fNamn, re.reservationNr, ar.artikelNr, " +
                                "re.reservationDatum, ar.titel, ar.artist, ar.isbn " +
                                "FROM reservation re  " +
                                "JOIN anvandare a ON a.anvandareNr = re.anvandareNr " +
                                "JOIN artikel ar ON ar.artikelNr = re.artikelNr " +
                                "WHERE a.anvandareNr = ?");

                stmt.setInt(1, Integer.parseInt(anvandareNr));
                // Execute the query
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
                // Display data in table
                results.setItems(data);

            } catch (SQLException e) {
                // Handle the exception
                BaseController.showAlert(Alert.AlertType.ERROR,
                        "Error",
                        "An error occurred while executing the query.: " + e.getMessage());
            } catch (NumberFormatException e) {
                // Handle the exception
                BaseController.showAlert(Alert.AlertType.ERROR,
                        "Error",
                        "Invalid user no: " + e.getMessage());
            }
        }
    }

    @FXML
    public void initialize() {
        // Get the current user
        String currentUser = UserSession.getCurrentUser();

        // Check if the user is logged in
        if (currentUser != null) {

            try {
                // Prepare the SQL statement to retrieve the user's type
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT anvandareTyp " +
                                "FROM anvandare " +
                                "WHERE anvandareNr = ?");

                stmt.setInt(1, Integer.parseInt(currentUser));
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String anvandareTyp = rs.getString("anvandareTyp");
                    AnvandareMessage anvandareMessage = null;

                    switch (anvandareTyp) {
                        case "Student":
                            anvandareMessage = new StudentAnvandare();
                            break;
                        case "Anstalld":
                            // Prepare the SQL statement to retrieve the employee's type
                            PreparedStatement stmt1 = conn.prepareStatement(
                                    "SELECT anstalldTyp " +
                                            "FROM anstalld " +
                                            "WHERE anvandareNr = ?");

                            stmt1.setInt(1, Integer.parseInt(currentUser));
                            ResultSet rs1 = stmt1.executeQuery();
                            // Check if the employee is a librarian
                            if (rs1.next() && rs1.getString("anstalldTyp").equals("Bibliotekarie")) {
                                anvandareMessage = new BibliotekarieAnvandare();
                            } else {
                                anvandareMessage = new AnstalldAnvandare();
                            }
                            break;
                        case "Larare":
                            anvandareMessage = new LarareAnvandare();
                            break;
                        case "Forskare":
                            anvandareMessage = new ForskareAnvandare();
                            break;
                        default:
                            System.out.println("Invalid user type");
                            break;
                    }
                    // Check if the user type is valid
                    if (anvandareMessage != null) {
                        // Print a message specific to the user's type, passing the user's first name as an argument
                        anvandareMessage.printMessage(LoginController.getfNamn());
                        // Set the user's message to be displayed
                        setUserMessage(anvandareMessage.getMessage());
                    }
                }

            } catch (SQLException | NumberFormatException e) {
                System.out.println("Error initializing the user: " + e.getMessage());
                e.printStackTrace();
            }
        }

        // Check if the user is a librarian
        if (currentUser != null) {
            try {

                // Prepare the SQL statement to retrieve the employee's type
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT anstalldTyp " +
                                "FROM anstalld " +
                                "WHERE anvandareNr = ?");

                stmt.setInt(1, Integer.parseInt(currentUser));
                // Execute the query
                ResultSet rs = stmt.executeQuery();

                if (rs.next() && rs.getString("anstalldTyp").equals("Bibliotekarie")) {
                    // User is a librarian, show the buttons by default
                    return;
                }

            } catch (SQLException | NumberFormatException e) {
                System.out.println("Error initializing the user: " + e.getMessage());
                e.printStackTrace();
            }
        }

        // User is not a librarian or not logged in, hide the buttons
        add.setVisible(false);
        editButton.setVisible(false);
        deleteButton.setVisible(false);
        lateButton.setVisible(false);
    }

    @FXML
    public void returnArticle() throws IOException {
        try {
            App.setRoot("return.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    public void late() throws IOException {
        try {
            App.setRoot("late.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    public void add() throws IOException {
        try {
            App.setRoot("add.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    public void edit() throws IOException {
        try {
            // Get the selected row
            ObservableList<String> selectedRow = (ObservableList<String>) results.getSelectionModel().getSelectedItem();
            if (selectedRow == null) {

                // Show an alert if no row is selected
                BaseController.showAlert(Alert.AlertType.ERROR,
                        "Error",
                        "Please select an object to edit.");
                return;

            } else {

                // Get the data from the selected row
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
                editController.receiveDetails(
                        artikelNr, sab, titel,
                        artist, utgava, artikelGenre,
                        artikelKategori, isbn, statusTyp);

                // Replace the content of the current window with edit.fxml content
                Scene scene = results.getScene();
                scene.setRoot(editRoot);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in edit() from AccountController class: " + e.getMessage());
        }
    }

    @FXML
    public void delete() {
        // Get the selected row
        ObservableList<String> selectedRow = (ObservableList<String>) results.getSelectionModel().getSelectedItem();
        if (selectedRow == null) {
            // Show an alert if no row is selected
            BaseController.showAlert(Alert.AlertType.ERROR,
                    "Error",
                    "Please select an object to delete.");
            return;
        }

        // Get the title of the object to be deleted
        String title = selectedRow.get(2); // assuming the title is in the third column

        // Ask the user for confirmation
        Optional<ButtonType> result = BaseController.showConfirmation(Alert.AlertType.CONFIRMATION,
                "Delete Object",
                "Are you sure you want to delete " + title + "?");

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User confirmed, delete the row from the table and database

            try {
                // Delete the row from the database
                PreparedStatement stmt = conn.prepareStatement(
                        "DELETE FROM artikel " +
                                "WHERE artikelNr = ?");

                // Assuming the article number is in the first column
                stmt.setInt(1, Integer.parseInt(selectedRow.get(0)));
                stmt.executeUpdate();
                results.getItems().remove(selectedRow);

                // Show a success message
                BaseController.showAlert(Alert.AlertType.INFORMATION,
                        "Success",
                        "The object " + title + " was deleted successfully.");
            } catch (SQLException e) {
                // Show an error message
                BaseController.showAlert(Alert.AlertType.ERROR,
                        "Error",
                        "An error occurred while deleting the object.");
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void searchDB() {

        String searchStr = searchField.getText();
        try {
            // Prepare the SQL statement
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
            // Add the data to the table
            results.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error loading the table: " + e.getMessage());
        }
    }

    public void borrow() {
        // Get the current user
        String currentUser = UserSession.getCurrentUser();

        //System.out.println("Current user: " + currentUser);

        // Get the selected item
        ObservableList<String> selectedItem = (ObservableList<String>) results.getSelectionModel().getSelectedItem();

        // Check if an item is selected
        if (selectedItem == null) {
            BaseController.showAlert(Alert.AlertType.INFORMATION,
                    "Error",
                    "Please select an item to borrow.");
            return;
        }

        String artikelNr = selectedItem.get(0);

        // Check if the item is available
        if (currentUser == null) {
            BaseController.showAlert(Alert.AlertType.INFORMATION,
                    "Error",
                    "Please log in to borrow an item.");
            return;
        }

        String anvandareNr = currentUser;

        try {
            // Insert into 'lan' table
            PreparedStatement insertLanStmt = conn.prepareStatement(
                    "INSERT INTO lan (anvandareNr) " +
                            "VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS);

            insertLanStmt.setString(1, anvandareNr);
            // Execute the query
            insertLanStmt.executeUpdate();

            // Get the generated lanNr
            ResultSet rs = insertLanStmt.getGeneratedKeys();
            rs.next();
            int lanNr = rs.getInt(1);

            // Insert into 'lanartikel' table
            PreparedStatement insertLanArtikelStmt = conn.prepareStatement(
                    "INSERT INTO lanartikel (lanNr, artikelNr) " +
                            "VALUES (?, ?)");

            insertLanArtikelStmt.setInt(1, lanNr);
            insertLanArtikelStmt.setString(2, artikelNr);
            // Execute the query
            insertLanArtikelStmt.executeUpdate();

            // Query to retrieve inlamningsDatum, laneDatum, and forfalloDatum
            PreparedStatement query = conn.prepareStatement(
                    "SELECT lanartikel.laneDatum, lanartikel.forfalloDatum " +
                            "FROM bibliotek.lanartikel " +
                            "WHERE lanartikel.lanNr = ? " +
                            "AND lanartikel.artikelNr = ?");

            query.setInt(1, lanNr);
            query.setString(2, artikelNr);
            // Execute the query
            ResultSet resultSet = query.executeQuery();

            // Move the cursor to the first row
            if (resultSet.next()) {
                // Extract data from the result set
                String laneDatum = resultSet.getString("laneDatum");
                String forfalloDatum = resultSet.getString("forfalloDatum");

                // Show the confirmation dialog
                Optional<ButtonType> result = BaseController.showConfirmation(Alert.AlertType.CONFIRMATION,
                        "Confirmation",
                        "Are you sure you want to borrow this article?");

                // Check if the user confirmed
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // Show the receipt with the retrieved data
                    String sab = selectedItem.get(1);
                    String titel = selectedItem.get(2);
                    String artist = selectedItem.get(3);
                    String utgava = selectedItem.get(4);
                    String artikelGenre = selectedItem.get(5);
                    String artikelKategori = selectedItem.get(6);
                    String isbn = selectedItem.get(7);
                    String statusTyp = selectedItem.get(8);

                    // Load the receipt view
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("receipt.fxml"));
                    Parent root = loader.load();

                    // Obtain the correct controller
                    ReceiptController receiptController = loader.getController();
                    receiptController.setReceiptData(anvandareNr, artikelGenre, artikelKategori,
                            artist, isbn, utgava, artikelNr,
                            sab, titel, laneDatum, forfalloDatum);

                    // Show the stage in a new window
                    Stage receiptStage = new Stage();
                    receiptStage.setTitle("Receipt");
                    receiptStage.setScene(new Scene(root));
                    receiptStage.showAndWait();
                }
            } else {
                BaseController.showAlert(Alert.AlertType.ERROR,
                        "Error",
                        "Borrowing this item is not allowed.");
            }
        } catch (SQLException e) {
            BaseController.showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        } catch (IOException e) {
            BaseController.showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    // Method to show the user message
    public void setUserMessage(String message) {
        userMessage.setText(message);
    }
}









