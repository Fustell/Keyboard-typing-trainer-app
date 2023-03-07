package com.roman.keyboardtypertrainer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileHandler {


    public static Deque<String> readWordsFromFile(String fileURI){

        Deque<String> words = new ArrayDeque<>();

        try {
            File myObj = new File(fileURI);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                words.addAll(List.of(myReader.nextLine().split(" ")));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File is not found.");
            e.printStackTrace();
        }
        return words;
    }

}
