/*package UnusedStuff;

import com.example.bibliotekdb3.App;
import com.example.bibliotekdb3.DatabaseConnector;
import com.example.bibliotekdb3.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.*;
import java.sql.Connection;

public class xAccountController {
    private Connection conn = DatabaseConnector.getConnection();

    @FXML
    private MenuItem back;

    @FXML
    private MenuItem close;

   // @FXML
    //private TableView<?> results;

    @FXML
    private Button showBorrows;

    @FXML
    private Button showReserved;

    @FXML
    private TableView results;


    @FXML
    public void showBorrows() {
        // Get the current user
        String currentUser = UserSession.getCurrentUser();  //Mazkin
        System.out.println("Current user: " + currentUser);   //Mazkin

        if (currentUser != null) { //Mazkin

            String anvandareNr = currentUser; //Mazkin
            try {
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT a.fNamn, l.lanNr, ar.artikelNr, la.laneDatum, la.forfalloDatum, " +
                                "ar.titel, ar.artist, ar.isbn " +
                                "FROM anvandare a " +
                                "JOIN lan l ON a.anvandareNr = l.anvandareNr " +
                                "JOIN lanartikel la ON l.lanNr = la.lanNr " +
                                "JOIN artikel ar ON la.artikelNr = ar.artikelNr " +
                                "WHERE a.anvandareNr = ?"
                );
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
        };
    }

    @FXML
    public void showReserved() {
        // Get the current user
        String currentUser = UserSession.getCurrentUser();  //Mazkin
        System.out.println("Current user: " + currentUser);   //Mazkin

        if (currentUser != null) { //Mazkin

            String anvandareNr = currentUser; //Mazkin
            try {
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT a.fNamn, re.reservationNr, ar.artikelNr, re.reservationDatum, ar.titel, ar.artist, ar.isbn " +
                                "FROM reservation re  " +
                                "JOIN anvandare a ON a.anvandareNr = re.anvandareNr " +
                                "JOIN artikel ar ON ar.artikelNr = re.artikelNr " +
                                "WHERE a.anvandareNr = ?"
                );
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
        };
    }

    @FXML
    public void are() throws IOException {
        App.setRoot("addRemoveEdit.fxml");
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

*/
