package gui.ListenerButtonSup;

import com.sun.tools.javac.Main;
import gui.GameType.GameManager;
import gui.GameType.PanelListGame;
import gui.Main.MainPanel;
import gui.Menu.MenuPrincipale;
import model.KropkiSudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class ListenerButtonSup implements MouseListener {

    private int indice;
    private static final String STRINGONE="Kropki Sudoku.\n Come giocare:\nOgni riga deve contenere tutti i numeri" +
            "da 1 ad n \nOgni colonna deve contenere tutti i numeri da 1 ad n \n" +
            "Se tra due celle c'è un pallino blu i numeri nelle celle adiacenti devono essere consecutivi\n" +
            "Se tra due celle c'è un pallino rosso i numeri nelle celle adiacenti devono essere uno il doppio dell'altro\n" +
            "Se non c'è un pallino tra due celle i due numeri non devono essere né consecutivi né doppi\n" +
            "Sviluppato da: Giovanni Rotondaro, Davide Ragona, Santo Locanto, Massimo Pio Iorio, Pietro Cofone.";
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
                JOptionPane.showMessageDialog(MainPanel.finestraPrincipale, "Hinted :)");
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
                JOptionPane.showMessageDialog(MainPanel.finestraPrincipale, "Risolto :)");
                break;
            case 4:
                int res = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler uscire dal gioco?", "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if(res == JOptionPane.YES_OPTION)
                    System.exit(0);
                break;
            case 5:
                System.out.println("Sto listenando");
                MainPanel.finestraPrincipale.setContentPane(new MenuPrincipale());
                MainPanel.finestraPrincipale.repaint();
                MainPanel.finestraPrincipale.revalidate();
                break;
            case 6:
                JOptionPane.showMessageDialog(MainPanel.finestraPrincipale, STRINGONE);
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
