package com.roman.keyboardtypertrainer;

import javafx.application.Platform;
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
    @FXML
    private Label wordsCounter;

    private Deque<String> dataText;
    private boolean flag;
    private int wordsPerMinute;
    private Long timeStart;

    public Controller(){
        this.dataText = new ArrayDeque<>();
        this.flag = false;
    }

    public void uploadWords(File file){
        try {
            File myObj = new File(file.toURI());
            Scanner myReader = new Scanner(myObj);
            this.dataText.clear();
            while (myReader.hasNextLine()) {
                this.dataText.addAll(List.of(myReader.nextLine().split(" ")));
            }
            this.textOutput.setText(this.dataText.pop());
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File is not found.");
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            File myObj = new File(Controller.class.getResource("data/words.txt").getFile());
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                this.dataText.addAll(List.of(myReader.nextLine().split(" ")));
            }
            this.textOutput.setText(this.dataText.pop());
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

        if (!this.flag){
            this.timeStart = System.currentTimeMillis();
            this.flag = true;
        }

        long timenow = System.currentTimeMillis();

        if(timenow >= (this.timeStart + 60*1000)){
            wordsCounter.setText(String.valueOf(this.wordsPerMinute));
            this.timeStart = System.currentTimeMillis();
            this.wordsPerMinute = 0;
        }


        if(this.textInput.getText().equals(" ")){
            this.textInput.setText("");
        }

        if(this.textInput.getText().equals(this.textOutput.getText()) && !this.dataText.isEmpty()){
            String inputString = this.dataText.pop();
            this.textOutput.setText(inputString);
            this.textInput.setPromptText(inputString);
            this.textInput.setText("");
            this.wordsPerMinute++;
        }
    }

    public void closeApp(ActionEvent event){
        Platform.exit();
        System.exit(0);
    }
}