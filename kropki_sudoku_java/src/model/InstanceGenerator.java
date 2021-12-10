package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InstanceGenerator {

    public boolean validateOnMinizinc(){
        return false;
    }

    // La classe builder si occupa solo di scrivere il file
    public static void instanceGenerator(){
        List<Integer[]> points = new ArrayList<>();
        String fileName = "resources/data/kropki_sudoku_data_"+
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyykkmmss"))+".dzn";
        System.out.println(fileName);
        File f = new File(fileName);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //BufferedWriter bw =
        // genera un numero casuale n da 4 a 9
        Random r = new Random();
        int n = r.nextInt(6)+4;

        // genera una coppia di coordinate x,y + un numero 0-1

        // While sulle soluzioni di minizinc

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
        points.add(newPoint);

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
            salvaSuFile(fileName,toWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void salvaSuFile(String filename, List<String> toWrite) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        for(String l : toWrite){
                bw.append(l);
                bw.newLine();
        }
        bw.close();
    }

    public void cleanFile(String filename){
        //TODO
    }


    public static void main(String[] args) {
        instanceGenerator();

    }
}
