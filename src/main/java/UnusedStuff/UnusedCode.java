/*
/*
    @FXML
    public void initialize() {

        // Connect to database
        String currentUser = UserSession.getCurrentUser();
        if (currentUser != null) {
            String anvandareNr = currentUser;

            // Get the user type
            try {
                PreparedStatement stmt = conn.prepareStatement("SELECT anstalldTyp FROM anstalld WHERE anvandareNr = ?");
                stmt.setInt(1, Integer.parseInt(anvandareNr));
                ResultSet rs = stmt.executeQuery();

                //  Check if the user is a bibliotekarie
                if (rs.next()) {
                    String anvandareTyp = rs.getString("anstalldTyp");

                    // Show/hide buttons based on anstalldTyp
                    // Here we can develop furture funcitons based on the user type
                    //System.out.println("Anvandare typ: " + anvandareTyp);

                    // If the user is not a bibliotekarie, hide the buttons
                    if (!"Bibliotekarie".equals(anvandareTyp)) {
                        add.setVisible(false);
                        editButton.setVisible(false);
                        deleteButton.setVisible(false);
                        lateButton.setVisible(false);
                    }
                } else {
                    add.setVisible(false);
                    editButton.setVisible(false);
                    deleteButton.setVisible(false);
                    lateButton.setVisible(false);
                }

            } catch (SQLException e) {
                // Handle the exception
                System.out.println("Error initializing the user: " + e.getMessage());
                e.printStackTrace();

            } catch (NumberFormatException e) {
                // Handle the exception
                System.out.println("Error initializing the user: " + e.getMessage());
                e.printStackTrace();

            }
        }
    }
*/
//Unused code
/*
      @FXML
     private TableView<?> results;


//search method normal
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

//edit method with editable row and delete button on each row
    public void edit() {

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
    }

//edit method with editable rows
    public void edit() {

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
    }

*/


//unused code
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





