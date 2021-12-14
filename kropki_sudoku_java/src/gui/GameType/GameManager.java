package gui.GameType;

import model.KropkiSudoku;

public class GameManager {

    private static GameManager instance;
    private KropkiSudoku kropki;
    private int[][] userInput;
    private ComponentMatrix [][]matrix;


    private GameManager(){}

    public static GameManager getInstance(){
        if(instance!=null){
            return instance;
        }
        instance=new GameManager();
        return instance;
    }

    public KropkiSudoku getKropki() {
        return kropki;
    }

    public int[][] getUserInput() {
        return userInput;
    }

    public void setKropki(KropkiSudoku kropki) {
        this.kropki = kropki;
    }

    public void setUserInput(int[][] userInput) {
        this.userInput = userInput;
    }

    public ComponentMatrix[][] getMatrix(){
        return matrix;
    }

    public void setMatrix(ComponentMatrix [][] matrix) {
        this.matrix = matrix;
    }


}
