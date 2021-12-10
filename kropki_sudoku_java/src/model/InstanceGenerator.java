package model;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static model.KropkiSudoku.SOLVER_PATH;

public class InstanceGenerator {

    public static final String MINIZINC_STATS = "minizinc --all-solutions -s ";
    public static final String MATRIX_COMPOSER = "resources/model/matrix_composer.mzn";
    public static final String DUMMY_INPUT = "resources/data/dummy_data.dzn";
    public static final String STAT_NUMBER_OF_SOLUTIONS = "%%%mzn-stat: solutions=";


    public static int validateOnMinizinc(String filename){
        int toReturn = -1;
        String line=null;
        Process process = null;
        try{

            if(System.getProperty("os.name").equalsIgnoreCase("Mac OS X")) {
                process = Runtime.getRuntime().exec(MINIZINC_STATS+"--solver COIN-BC "+ SOLVER_PATH+" "+filename);
            }
            else if(System.getProperty("os.name").contains("Windows")) {
                System.out.println("Installa linux ;)");
                System.err.println(MINIZINC_STATS+ SOLVER_PATH+" "+filename);
                process = Runtime.getRuntime().exec(MINIZINC_STATS+ SOLVER_PATH+" "+filename);
            }
            else if(System.getProperty("os.name").equalsIgnoreCase("Linux")) {
                process = Runtime.getRuntime().exec(MINIZINC_STATS+ SOLVER_PATH+" "+filename);
            }
            else {
                System.out.println("------------------------ ERRORE OS ------------------------------------");
            }


            List<String> output = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = reader.readLine()) != null) {
                output.add(line);
            }
            toReturn = scanResultLines(output);
            process.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } catch (InterruptedException e) {
            return -1;
        }

        return toReturn;


    }

    private static int scanResultLines(List<String> result) {
        for (String tmp : result){
            System.out.println("READING : " +tmp);
            if(tmp != null && tmp.contains(InstanceGenerator.STAT_NUMBER_OF_SOLUTIONS)){
                System.out.println("EVALUATING: "+tmp);
                String numberOfSolutions = tmp.replaceAll(InstanceGenerator.STAT_NUMBER_OF_SOLUTIONS, "").replaceAll("\\n","");
                return Integer.parseInt(numberOfSolutions);
            }
        }

        return -1;
    }

    public static Integer[] generateNewPoint(int n){
        Random r = new Random();
        int x = r.nextInt(n - 1) + 1;
        int y = r.nextInt(n - 1) + 1;
        //decidere se orizzontale o verticale (0-1)
        boolean horizontal = r.nextBoolean();
        // decidere se doppio o consecutivo (0-1) -- 0 per il doppio -- 1 per il consecutivo
        int doubleOrConsecutive = r.nextInt(2);
        int x2 = x;
        int y2 = y;
        if (horizontal) {
            y2++;
        } else x2++;
        System.out.println("Adding point: "+x + " " + y + " " + x2 + " " + y2 + " " + doubleOrConsecutive);
        Integer[] newPoint = {x,y,x2,y2,doubleOrConsecutive};
        return newPoint;
    }


    public static void instanceGenerator() {

        List<Integer[]> points = new ArrayList<>();
        String fileName = "resources/data/kropki_sudoku_data_"+
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyykkmmss"))+".dzn";
        System.out.println(fileName);
        File f = new File(fileName);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("IOException");
        }
        //BufferedWriter bw =
        // genera un numero casuale n da 4 a 9
        Random r = new Random();
        //int n = 6;
        int n = r.nextInt(6)+4;

        int[][] input = new int[n][n];
        int i = r.nextInt(n);
        int j = r.nextInt(n);
        int value = r.nextInt(n)+1;
        input[i][j] = value;
        List<String> toWrite =writeMatrixOnFile(DUMMY_INPUT, n,input);

        try {
            cleanFile(DUMMY_INPUT);
            saveOnFile(DUMMY_INPUT, toWrite);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        input = MinizincUtility.callMiniZinc(MATRIX_COMPOSER, DUMMY_INPUT, n);
        System.out.println("ok");
        for (int row=0 ; row < n; row++ ) {
            for (int column=0 ; column < n ; column++ ) {
                // Orizzontale DOPPIO (0)
                if (column+1 <n ) {
                    if (input[row][column] == 2 * input[row][column + 1]
                            || 2 * input[row][column] == input[row][column + 1]) {
                        Integer[] tmp = new Integer[5];
                        tmp[0] = row + 1;
                        tmp[1] = column + 1;
                        tmp[2] = row + 1;
                        tmp[3] = column + 2;
                        tmp[4] = 0; //
                        points.add(tmp);
                    }
                }
                // Verticale DOPPIO (0)
                if (row+1<n) {
                    if (input[row][column] == 2 * input[row + 1][column]
                            || 2 * input[row][column] == input[row + 1][column]) {
                        Integer[] tmp = new Integer[5];
                        tmp[0] = row + 1;
                        tmp[1] = column + 1;
                        tmp[2] = row + 2;
                        tmp[3] = column + 1;
                        tmp[4] = 0; //
                        points.add(tmp);
                    }
                }

                // Orizzontale CONSECUTIVO (1)
                if (column+1 <n) {
                    if ((input[row][column] - input[row][column + 1] == 1
                            || input[row][column] - input[row][column + 1] == -1)
                            && (!(input[row][column] == 1 && input[row][column + 1] == 2) &&
                            (!(input[row][column] == 2 && input[row][column + 1] == 1)))) {
                        Integer[] tmp = new Integer[5];
                        tmp[0] = row + 1;
                        tmp[1] = column + 1;
                        tmp[2] = row + 1;
                        tmp[3] = column + 2;
                        tmp[4] = 1; //
                        points.add(tmp);
                    }
                }

                // Verticale CONSECUTIVO (1)
                if (row+1 <n ) {
                    if ((input[row][column] - input[row + 1][column] == 1
                            || input[row][column] - input[row + 1][column] == -1) &&
                            (!(input[row][column] == 1 && input[row + 1][column] == 2) &&
                                    (!(input[row][column] == 2 && input[row + 1][column] == 1)))) {
                        Integer[] tmp = new Integer[5];
                        tmp[0] = row + 1;
                        tmp[1] = column + 1;
                        tmp[2] = row + 2;
                        tmp[3] = column + 1;
                        tmp[4] = 1; //
                        points.add(tmp);
                    }
                }
            }
        }
        writePointsOnFile(fileName,n,points);
        int validationResult = validateOnMinizinc(fileName);
        // if validationResult != 1
            // rifai


    }


    private static List<String> writeMatrixOnFile(String fileName, int n, int[][] input) {
        List<String> toWrite = new ArrayList<>();
        toWrite.add("n="+n+";");
        String matrix = "input=[|";
        for (int i=0 ; i < n; i++){
            for(int j=0; j < n-1; j++){
                matrix+=input[i][j]+",";
            }
            matrix+=input[i][n-1]+"|";
        }
        matrix+="];";
        toWrite.add(matrix);
        return toWrite;
    }

    private static void writePointsOnFile(String fileName, int n, List<Integer[]> points) {
        List<String> toWrite = new ArrayList<>();
        toWrite.add("n="+n+";");
        String matrix = "input=[|";
        for (int i=0 ; i < n; i++){
            for(int j=0; j < n-1; j++){
                matrix+="0,";
            }
            matrix+="0|";
        }
        matrix+="];";
        toWrite.add(matrix);

        String pointsString = "points=[|";
        for (Integer[] point : points){
            pointsString+=point[0].toString()+","+
                    point[1].toString()+","+
                    point[2].toString()+","+
                    point[3].toString()+","+
                    point[4].toString()+"|";
        }
        pointsString+="];";
        toWrite.add(pointsString);

        System.out.println(toWrite);

        try {
            cleanFile(fileName);
            saveOnFile(fileName,toWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    static void saveOnFile(String filename, List<String> toWrite) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        for(String l : toWrite){
                bw.append(l);
                bw.newLine();
        }
        bw.close();
    }

    public static void cleanFile(String filename) throws FileNotFoundException{
        File tmp = new File(filename);
        PrintWriter writer = new PrintWriter(tmp);
        writer.print("");
        writer.close();
    }


    public static void main(String[] args) {
        instanceGenerator();

    }
}
