module com.roman.keyboardtypingtrainer.keyboardtypingtrainer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.roman.keyboardtypingtrainer to javafx.fxml;
    exports com.roman.keyboardtypingtrainer;
}