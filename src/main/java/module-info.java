module com.example.safe {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.safe to javafx.fxml;
    exports com.example.safe;
}