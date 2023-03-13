package com.roman.keyboardtypingtrainer;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileHandler {


    public static Deque<String> readWordsFromFile(String fileURI){

        Deque<String> words = new ArrayDeque<>();

        try {
            InputStream is = FileHandler.class.getResourceAsStream("data/words.txt");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            Stream<String> lines = br.lines();
            lines.forEach(s -> {words.addAll(List.of(s.split(" ")));});
        } catch (Exception e) {
            System.out.println("File is not found.");
            e.printStackTrace();
        }
        return words;
    }

    public static Deque<String> readWordsFromExternalFile(String fileURI){

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
