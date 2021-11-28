package com.knowledge.project.kropkisudoku.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class KropkiSudoku {

    public static final String PATH = "kropkisudoku/src/main/resources/kropki_sudoku_";
    public static final String SOLVER_PATH = "kropkisudoku/src/main/resources/kropki_sudoku.mzn";
    public static final String EXTENSION = ".dzn";
    public int id;
    private int[][] matrix;
    private int n;
    private List<Integer[]> points;


    private String[] read(int id) throws  IOException{
        File f = new File(PATH + id + EXTENSION);
        String[] lines = new String[3];

        BufferedReader reader = new BufferedReader(new FileReader(f));
        int readLines = 0;
        while (reader.ready()) {
            lines[readLines]= reader.readLine();
            readLines++;

        }
        reader.close();
        return lines;
    }

    /*
    public KropkiSudoku(int n, int[][] matrix, int[][] points){

    }*/
    /*
    public KropkiSudoku() throws IOException {
        // Take one random
    }*/
    public KropkiSudoku(int id) {
        this.id = id;

        try{
            String[] s = read(id);
            this.n = initN(s[0]);
            this.matrix = new int[this.n][this.n];
            this.matrix = initMatrix(s[1]);
            this.points = initPoints(s[2]);

        }
        catch (IOException exception){
            exception.printStackTrace();
        }

    }

    private int[][] initMatrix(String s) {
        int[][] mat = new int[this.n][this.n];
        String newS;
        newS= s.replaceAll("\\s+","");
        int equals = s.indexOf('=');
        newS = newS.substring(equals+1, s.length());
        newS = newS.replaceAll("\\[\\|","");
        newS = newS.replaceAll("\\|\\]","");
        newS = newS.replaceAll(";","");

        String[] splitted = newS.split("\\|");
        int iElements=0;
        int jElements=0;
        for(String row : splitted){
            String[] splittedRow = row.split(",");
            for (String cell : splittedRow){
                mat[iElements][jElements] = Integer.parseInt(cell);
                jElements++;
            }
            jElements=0;
            iElements++;

        }

        return mat;
    }

    private List<Integer[]> initPoints(String s) {
       List returnList = new ArrayList<>();
        String newS;
        newS= s.replaceAll("\\s+","");
        int equals = s.indexOf('=');
        newS = newS.substring(equals+1, s.length());
        newS = newS.replaceAll("\\[\\|","");
        newS = newS.replaceAll("\\|\\]","");
        newS = newS.replaceAll(";","");

        String[] splitted = newS.split("\\|");
        int jElements=0;
        for(String row : splitted){
            String[] splittedRow = row.split(",");
            Integer[] point = new Integer[5];
            for (String cell : splittedRow){
                point[jElements] = Integer.parseInt(cell);
                jElements++;
            }
            returnList.add(point);
            jElements=0;
        }

        return returnList;
    }



    private int initN (String s){
        //n=8;
        s = s.replaceAll("\\s+","");
        String newS = ""+s.charAt(2);
        return Integer.parseInt(newS);
    }

    public String callMiniZinc(){

        String line=null;
        try{
            // if windows
            Process process = Runtime.getRuntime().exec("minizinc "+ SOLVER_PATH+" "+PATH+this.id+EXTENSION);
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    return line;
    }


    public void sendInitialSudoku(){

    }

    /*
    public void sendSolvedSudoku(){
        return matrix.toString();
    }
    */

}

