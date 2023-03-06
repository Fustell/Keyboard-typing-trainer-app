package com.example.keyboardtypertrainer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;

public class HelloController {
    @FXML
    public Label mylabel;

    @FXML
    public TextArea myTextArea;

    public void textAreaTyped(KeyEvent event){
        System.out.println(myTextArea.getText());
    }
}