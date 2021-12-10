package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KropkiSudoku {

    public static final String PATH = "resources/data/kropki_sudoku_data_";
    public static final String SOLVER_PATH = "resources/model/kropki_sudoku.mzn";
    public static final String EXTENSION = ".dzn";
    public int id;
    private int[][] initialMatrix;
    private int[][] solvedMatrix;
    private int n;
    private List<Integer[]> points;

    // PRIVATE UTILITY FUNCTIONS ***************************************************************************************
    private String[] read(int id) throws  IOException{
        File f = new File(PATH + id + EXTENSION);

        BufferedReader reader = new BufferedReader(new FileReader(f));

        StringBuilder sb = new StringBuilder();

        while (reader.ready()) {
            sb.append(reader.readLine());
        }
        reader.close();


        return splitByNewline(sb.toString());
    }

    private String[] splitByNewline(String s) {
        System.out.println(s);

        String newS;
        newS= s.replaceAll("\\n+","");
        String[] splitted = newS.split(";");

        return splitted;
    }

    private String[] split (String s){
        int equals = s.indexOf('=');
        s = s.substring(equals+1, s.length());
        s = s.replaceAll("\\[\\|","");
        s = s.replaceAll("\\|\\]","");
        s = s.replaceAll(";","");
        return s.split("\\|");
    }

    // END PRIVATE UTILITY FUNCTIONS ***********************************************************************************

    // CONSTRUCTORS AND INIT PHASE *************************************************************************************

    public KropkiSudoku(int id) {
        this.id = id;
        this.initAll();

    }

    public KropkiSudoku(){
        this.id = generateRandomFromList();
        this.initAll();
    }

    private void initAll() {

        try{
            String[] s = read(id);
            this.n = initN(s[0]);
            initPoints(s[2]);
            initMatrix(s[1]);
        }
        catch (IOException exception){
            exception.printStackTrace();
        }

    }
    private void initMatrix(String s) {
        this.initialMatrix = new int[this.n][this.n];
        String newS;
        newS= s.replaceAll("\\s+","");
        int iElements=0;
        int jElements=0;
        for(String row : split(newS)){
            String[] splittedRow = row.split(",");
            for (String cell : splittedRow){
                this.initialMatrix[iElements][jElements] = Integer.parseInt(cell);
                jElements++;
            }
            jElements=0;
            iElements++;

        }

        callMiniZinc();
    }




    private void initPoints(String s) {
        this.points = new ArrayList<>();
        String newS= s.replaceAll("\\s+","");
        int jElements=0;
        for(String row : split(newS)){
            String[] splittedRow = row.split(",");
            Integer[] point = new Integer[5];
            for (String cell : splittedRow){
                point[jElements] = Integer.parseInt(cell);
                jElements++;
            }
            this.points.add(point);
            jElements=0;
        }
    }



    private int initN (String s){
        s = s.replaceAll("\\s+","");
        String newS = ""+s.charAt(2);
        return Integer.parseInt(newS);
    }

    // END CONSTRUCTORS AND INIT PHASE *********************************************************************************

    public int generateRandomFromList() {

        File folder = new File("resources/data");
        File[] listOfFiles = folder.listFiles();
        Random r = new Random();
        return r.nextInt(listOfFiles.length);

    }





    public void callMiniZinc(){

        String line=null;
        Process process = null;
        try{

            // Questo si può togliere perché tanto è uguale in tutti gli OS

            if(System.getProperty("os.name").equalsIgnoreCase("Mac OS X")) {
                process = Runtime.getRuntime().exec("minizinc "+ SOLVER_PATH+" "+PATH+this.id+EXTENSION);
            }
            else if(System.getProperty("os.name").contains("Windows")) {
                System.out.println("Installa linux ;)");
                process = Runtime.getRuntime().exec("minizinc "+ SOLVER_PATH+" "+PATH+this.id+EXTENSION);
            }
            else if(System.getProperty("os.name").equalsIgnoreCase("Linux")) {
                process = Runtime.getRuntime().exec("minizinc "+ SOLVER_PATH+" "+PATH+this.id+EXTENSION);
            }
            else {
                System.out.println("------------------------ ERRORE OS ------------------------------------");
            }


            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            System.out.println("output.toString() = " + output.toString());
            String result = output.toString();
            manageMinizincResult(result);
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void manageMinizincResult(String result) {
        this.solvedMatrix = new int[this.n][this.n];
        result= result.replaceAll("----------","");
        String[] rows = result.split("\\n");
        int i=0;
        for(String row: rows){
            String[] columns = row.split(",");
            int j=0;
            for (String column : columns){
                column= column.replaceAll("\\s+","");
                this.solvedMatrix[i][j] = Integer.parseInt(column);
                j++;
            }
            i++;
        }
    }

    public int[][] sendSolvedSudoku(){
        return this.solvedMatrix;
    }

    // fills the input matrix with one random additional cell filled
    public int[][] sendHint(int[][] userInput) {
     /*   if (userInput.length == 0 || userInput[0].length == 0) return false;
        if (!this.validate(userInput)) return false;
        if (!this.isComplete(userInput)) return false;
     */ Random r = new Random();
        int randomI =  r.nextInt(userInput.length);
        int randomJ = r.nextInt(userInput[0].length);
        while (userInput[randomI][randomJ] != 0 ) {
            randomI = r.nextInt(userInput.length);
            randomJ = r.nextInt(userInput[0].length);
        }
        userInput[randomI][randomJ] = this.solvedMatrix[randomI][randomJ];
        return userInput;

    }

    public boolean validate(int[][] userInput) {
        for (int i=0 ; i < userInput.length ; i++) {
            for(int j=0 ; j < userInput[0].length; j++) {
                if (userInput[i][j] != 0 && userInput[i][j] != this.solvedMatrix[i][j] ) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isComplete(int[][] userInput) {
        for (int i=0 ; i < userInput.length ; i++) {
            for(int j=0 ; j < userInput[0].length; j++) {
                if (userInput[i][j] != this.solvedMatrix[i][j] ) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] getInitialMatrix(){
        return this.initialMatrix;
    }
    public int[][] getSolvedMatrix(){
        return this.solvedMatrix;
    }


    public static void main(String[] args) {
        System.out.println("OK");
        KropkiSudoku ks = new KropkiSudoku(3);
        int[][] myMatrix = ks.getInitialMatrix();
        printMatrix(myMatrix);
        while (!ks.isComplete(myMatrix)){
            if (ks.validate(myMatrix)) {
                System.out.println("Validation OK");
                myMatrix = ks.sendHint(myMatrix);
            }

            printMatrix(myMatrix);
        }

        System.out.println("Right Solution: ");
        int[][] rightSolution = ks.getSolvedMatrix();
        printMatrix(rightSolution);
    }

    private static void printMatrix(int[][] myMatrix) {
        for(int i=0 ; i < myMatrix.length; i++) {
            for(int j=0; j < myMatrix[0].length ; j++) {
                System.out.print(myMatrix[i][j]+" ");
            }
            System.out.print("\n");
        }
        System.out.println("--------------------------------------");
    }
}




