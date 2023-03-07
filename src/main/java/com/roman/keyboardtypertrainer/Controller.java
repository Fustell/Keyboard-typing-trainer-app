package com.roman.keyboardtypertrainer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class Controller  implements Initializable {

    @FXML
    private Label textOutput;
    @FXML
    private TextField textInput;

    private Deque<String> dataText;

    public Controller(){
        this.dataText = new ArrayDeque<>();
    }

    public void uploadWords(File file){
        try {
            File myObj = new File(file.toURI());
            Scanner myReader = new Scanner(myObj);
            this.dataText.clear();
            while (myReader.hasNextLine()) {
                this.dataText.addAll(List.of(myReader.nextLine().split(" ")));
            }
            textOutput.setText(this.dataText.pop());
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File is not found.");
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            File myObj = new File("E:\\JavaProject\\Keyboard-Typer-trainer\\src\\main\\java\\com\\roman\\keyboardtypertrainer\\data\\words.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                this.dataText.addAll(List.of(myReader.nextLine().split(" ")));
            }
            textOutput.setText(this.dataText.pop());
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File is not found.");
            e.printStackTrace();
        }
    }

    public void updateWords(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(new Stage());
        uploadWords(file);
    }

    public void textAreaTyped(KeyEvent event){

        if(textInput.getText().equals(textOutput.getText()) && !this.dataText.isEmpty()){
            String inputString = this.dataText.pop();
            textOutput.setText(inputString);
            textInput.setPromptText(inputString);
            textInput.setText("");
        }
    }
}