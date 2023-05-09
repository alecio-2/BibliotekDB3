module com.example.bibliotekdb3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires java.persistence;


    opens com.example.bibliotekdb3 to javafx.fxml;
    exports com.example.bibliotekdb3;
    exports UnusedStuff;
    opens UnusedStuff to javafx.fxml;
}