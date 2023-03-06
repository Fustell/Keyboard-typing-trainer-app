module com.example.keyboardtypertrainer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.keyboardtypertrainer to javafx.fxml;
    exports com.example.keyboardtypertrainer;
}