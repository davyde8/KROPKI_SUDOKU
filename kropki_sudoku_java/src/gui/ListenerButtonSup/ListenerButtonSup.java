package gui.ListenerButtonSup;

import com.sun.tools.javac.Main;
import gui.GameType.GameManager;
import gui.Main.MainPanel;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
                System.out.println("ciao");
                if(!(GameManager.getInstance().getKropki().validate(GameManager.getInstance().getUserInput()))){
                    JOptionPane.showMessageDialog(MainPanel.finestraPrincipale,"Error!!");
                }
                break;
            case 2:
                break;
            case 3:
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
