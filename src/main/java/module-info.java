module com.example.keyboardtypertrainer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.roman.keyboardtypertrainer to javafx.fxml;
    exports com.roman.keyboardtypertrainer;
}