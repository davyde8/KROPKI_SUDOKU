package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KropkiSudoku {

    public static final String PATH = "resources/data/kropki_sudoku_data_";
    public static final String SOLVER_PATH = "resources/model/kropki_sudoku.mzn";
    public static final String EXTENSION = ".dzn";
    public String id;
    private int[][] initialMatrix;
    private int[][] solvedMatrix;
    private int n;
    private List<Integer[]> points;

    // PRIVATE UTILITY FUNCTIONS ***************************************************************************************
    private String[] read(String id) throws  IOException{
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

        return newS.split(";");
    }

    private String[] split (String s){
        int equals = s.indexOf('=');
        s = s.substring(equals+1);
        s = s.replaceAll("\\[\\|","");
        s = s.replaceAll("\\|]","");
        s = s.replaceAll(";","");
        return s.split("\\|");
    }

    // END PRIVATE UTILITY FUNCTIONS ***********************************************************************************

    // CONSTRUCTORS AND INIT PHASE *************************************************************************************

    public KropkiSudoku(String id) {
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

        this.solvedMatrix = MinizincUtility.callMiniZinc(SOLVER_PATH, PATH+id+EXTENSION,n);
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

    public String generateRandomFromList() {

        File folder = new File("resources/data");
        File[] listOfFiles = folder.listFiles();
        Random r = new Random();
        return extractId(listOfFiles[r.nextInt(listOfFiles.length)]);

    }

    private static String extractId(File file) {
        return file.getName().replaceAll("kropki_sudoku_data_","").replaceAll(".dzn","");
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

    public List<Integer[]> validate(int[][] userInput) {
        List<Integer[]> notValidCells = new ArrayList<>();
        for (int i=0 ; i < userInput.length ; i++) {
            for(int j=0 ; j < userInput[0].length; j++) {
                if (userInput[i][j] != 0 && userInput[i][j] != this.solvedMatrix[i][j] ) {
                    Integer[] tmp = new Integer[2];
                    tmp[0] = i;
                    tmp[1] = j;
                    notValidCells.add(tmp);
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
        KropkiSudoku ks = new KropkiSudoku("10122021235858");
        int[][] myMatrix = ks.getInitialMatrix();
        printMatrix(myMatrix);
        while (!ks.isComplete(myMatrix)){
            if (ks.validate(myMatrix).size() == 0) {
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

    public List<Integer[]> getPoints(){
        return this.points;
    }

    public int getN(){
        return n;
    }
}




