package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MinizincUtility {

    public static int[][] callMiniZinc(String solver, String data, int n){

        String line=null;
        String result ="";
        Process process = null;
        try{
            if(System.getProperty("os.name").equalsIgnoreCase("Mac OS X")) {
                process = Runtime.getRuntime().exec("/Applications/MiniZincIDE.app/Contents/Resources/minizinc "+ solver+" "+data);
            }
            else if(System.getProperty("os.name").contains("Windows")) {
                process = Runtime.getRuntime().exec("minizinc "+ solver+" "+data);
            }
            else if(System.getProperty("os.name").equalsIgnoreCase("Linux")) {
                process = Runtime.getRuntime().exec("minizinc "+ solver+" "+data);
            }
            else {
                System.out.println("------------------------ ERRORE OS ------------------------------------");
            }


            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            System.out.println("output.toString() = " + output);
            result = output.toString();

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return manageMinizincResult(result, n);
    }

    private static int[][] manageMinizincResult(String result, int n) {
        int[][] fromMiniZinc = new int[n][n];
        result= result.replaceAll("----------","");
        String[] rows = result.split("\\n");
        int i=0;
        for(String row: rows){
            String[] columns = row.split(",");
            int j=0;
            for (String column : columns){
                column= column.replaceAll("\\s+","");
                fromMiniZinc[i][j] = Integer.parseInt(column);
                j++;
            }
            i++;
        }
        return fromMiniZinc;
    }
}
