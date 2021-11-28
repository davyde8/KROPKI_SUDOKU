package com.knowledge.project.kropkisudoku;

import com.knowledge.project.kropkisudoku.model.KropkiSudoku;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class KropkisudokuApplication {

    public static void main(String[] args) {
        SpringApplication.run(KropkisudokuApplication.class, args);

        KropkiSudoku giovannirotondaro = new KropkiSudoku(1);
    }

}