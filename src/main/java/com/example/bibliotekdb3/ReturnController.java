package com.example.bibliotekdb3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.jar.JarEntry;

public class ReturnController extends BaseController {

    private Connection conn = DatabaseConnector.getConnection();

    String currentUser;

    @FXML
    private TextField inputField;

    @FXML
    private Button returnButton;

    public void returnArticle() throws SQLException {
        String currentUser = UserSession.getCurrentUser();
        System.out.println("Return made by user number: " + currentUser);
        System.out.println("Article returned: " + inputField.getText());
        String input = inputField.getText();
        String sql1 = "SELECT lan.lanNr FROM lan JOIN lanartikel ON lan.lanNr = lanartikel.lanNr WHERE lan.anvandareNr = ? and lanartikel.artikelNr = ?;";
        //String sql1 = "JOIN lanartikel ON lan.lanNr = lanartikel.lanNr
        //WHERE lan.anvandareNr = 2 AND lanartikel.artikelNr = 1
        //ORDER BY lanartikel.laneDatum DESC
        //LIMIT 1;";
        PreparedStatement stmt1 = conn.prepareStatement(sql1);
        try {
            stmt1.setString(1, currentUser);
            stmt1.setString(2, input);
            ResultSet rs = stmt1.executeQuery();

            if (rs.next()) {
                // Retrieve the value of lanNr from the result set
                String currentLanNr = rs.getString("lanNr");
                System.out.println("Current Lan Nr: " + currentLanNr);



                String sql = "INSERT INTO inlamningsdatum (lanNr, artikelNr, inlamningsDatum) VALUES (?, ?, ?)";

                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, currentLanNr);
                stmt.setString(2, input);
                stmt.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
                stmt.executeUpdate();

                System.out.println("Article returned successfully.");
            } else {
                System.out.println("No active lanNr found for the current user.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


/*
    public void returnArticle() throws SQLException {
        String currentUser = UserSession.getCurrentUser();
        System.out.println("currentUser");
        System.out.println("Return made by user number: " + currentUser);
        System.out.println("Article returned: " + inputField.getText());

        String sql1 = "SELECT lanNr FROM lan JOIN lanartikel ON lanNr WHERE anvandareNr = ?";

        PreparedStatement stmt1 = conn.prepareStatement(sql1);
        try {
            stmt1.setString(1, currentUser);
            stmt1.executeUpdate();
            //write the result of the query to a variable currentLanNr
            String currentLanNr = stmt1.toString();
            System.out.println(currentLanNr);

        } catch (Exception e) {
            e.printStackTrace();
        }


        String input = inputField.getText();

        String sql = "INSERT INTO inlamningsdatum (lanNr, artikelNr) VALUES (?,?)";

        PreparedStatement stmt = conn.prepareStatement(sql);


        try {
            stmt.setString(1, stmt1.toString());
            stmt.setString(2, input);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }




    }
*/
}
