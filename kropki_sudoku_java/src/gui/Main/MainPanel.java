package gui.Main;

import gui.Dimensioni.Dimensioni;
import gui.Menu.MenuPrincipale;

import javax.swing.*;
import java.awt.*;

public class MainPanel {
    public static JFrame finestraPrincipale;
    public static void main(String[] args) {
        JFrame finestra=new JFrame("KropkiSudoku");
        finestraPrincipale=finestra;
        MenuPrincipale p=new MenuPrincipale();
        finestra.add(p, BorderLayout.CENTER);
        finestra.setSize(new Dimension(Dimensioni.WIDTH,Dimensioni.HEIGHT));
        finestra.setVisible(true);
        finestra.setLocationRelativeTo(null);
        finestra.setResizable(false);
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
