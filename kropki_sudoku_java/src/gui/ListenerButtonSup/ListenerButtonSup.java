package gui.ListenerButtonSup;

import com.sun.tools.javac.Main;
import gui.GameType.GameManager;
import gui.Main.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class ListenerButtonSup implements MouseListener {

    private int indice;

    public ListenerButtonSup(int indice){
        this.indice=indice;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (indice){
            case 1:
                List<Integer[]> notValidCells =
                        GameManager.getInstance().getKropki().validate(GameManager.getInstance().getUserInput());
                System.out.println( GameManager.getInstance().getKropki().isComplete(GameManager.getInstance().getUserInput()));
                if ( GameManager.getInstance().getKropki().isComplete(GameManager.getInstance().getUserInput())) {
                    JOptionPane.showMessageDialog(MainPanel.finestraPrincipale, "Complimenti, hai vinto :)");
                }
                for (Integer[] cell : notValidCells) {
                    GameManager.getInstance().getMatrix()[cell[1]][cell[0]].setTextColor(Color.RED);
                }
                break;
            case 2:
                int[][] newMatrix =
                        GameManager.getInstance().getKropki().sendHint(GameManager.getInstance().getUserInput());
                for (int i=0 ; i < newMatrix.length; i++){
                    for (int j=0 ; j < newMatrix[0].length; j++){
                        GameManager.getInstance().getMatrix()[j][i].settaNumero(String.valueOf(newMatrix[i][j]));
                        GameManager.getInstance().getUserInput()[i][j] = newMatrix[i][j];
                    }
                }
                break;
            case 3:
                int[][] solvedMatrix =
                        GameManager.getInstance().getKropki().getSolvedMatrix();
                for (int i=0 ; i < solvedMatrix.length; i++){
                    for (int j=0 ; j < solvedMatrix[0].length; j++){
                        GameManager.getInstance().getMatrix()[j][i].settaNumero(String.valueOf(solvedMatrix[i][j]));
                        GameManager.getInstance().getUserInput()[i][j] = solvedMatrix[i][j];
                    }
                }
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
