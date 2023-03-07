package com.roman.keyboardtypertrainer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.ResourceBundle;

public class Controller  implements Initializable {

    @FXML
    private Label textOutput,wordsCounter, sumAllNumbers, counterMistake;
    @FXML
    private TextField textInput;
    @FXML
    private AnchorPane scenePanel;

    private Deque<String> words;
    private boolean flag;
    private int wordsPerMinute, numberWords, mistakes;
    private Long timeStart;

    public Controller(){
        this.words = new ArrayDeque<>();
        this.flag = false;
    }

    public void uploadWords(File file){
        this.words.clear();
        this.words = FileHandler.readWordsFromFile(String.valueOf(file));
        this.textOutput.setText(this.words.pop());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.words = FileHandler.readWordsFromFile(Controller.class.getResource("data/words.txt").getFile());
        this.textOutput.setText(this.words.pop());
    }

    public void updateWords(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(new Stage());
        uploadWords(file);
    }

    public void textAreaTyped(KeyEvent event){

        // start recording
        if (!this.flag){
            this.timeStart = System.currentTimeMillis();
            this.flag = true;
        }

        long timenow = System.currentTimeMillis();

        // checks if 1 minute passed
        if(timenow >= (this.timeStart + 60*1000)){
            wordsCounter.setText(String.valueOf(this.wordsPerMinute));
            this.timeStart = System.currentTimeMillis();
            this.wordsPerMinute = 0;
        }


        // removes spaces before word
        if(this.textInput.getText().equals(" ")){
            this.textInput.setText("");
        }

        // if textInput doesn't coincide with textOutput
        if(!this.textOutput.getText().startsWith(this.textInput.getText())){
            mistakes++;
            this.counterMistake.setText(String.valueOf(mistakes));
        }

        // updates text in label and changes word
        if(this.textInput.getText().equals(this.textOutput.getText()) && !this.words.isEmpty()){
            String inputString = this.words.pop();
            this.textOutput.setText(inputString);
            this.textInput.setPromptText(inputString);
            this.textInput.setText("");
            this.wordsPerMinute++;
            this.numberWords++;
            sumAllNumbers.setText(String.valueOf(numberWords));
        }
    }

    public void closeApp(ActionEvent event){

        Stage stage  = (Stage) scenePanel.getScene().getWindow();
        stage.close();
    }
}