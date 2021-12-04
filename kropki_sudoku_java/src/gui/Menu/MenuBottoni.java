package gui.Menu;

import gui.Dimensioni.Dimensioni;
import gui.GameType.PanelGenerateLevel;
import gui.GameType.PanelListGame;
import gui.Main.MainPanel;
import gui.GameType.PanelRandomGame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuBottoni extends JButton implements MouseListener {
    public static boolean multiplayer=false;
    private int indiceBottoni;

    public MenuBottoni(int i) {
        //IMPOSTO IL TESTO DEI VARI BOTTONI
        switch (i) {
            case 0:
                this.setText("PLAY RANDOM");
                break;
            case 1:
                this.setText("PLAY");
                break;
            case 2:
                this.setText("GENERATE LEVEL");
                break;
            case 3:
                this.setText("QUIT");
                break;
        }
        Border border = BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK);
        this.setBorder(border);
        this.setMaximumSize(new Dimension(((Dimensioni.WIDTH / 100) * 30), ((Dimensioni.HEIGHT / 100) * 8)));
        this.setBackground(Color.RED);
        this.setOpaque(true);
        this.setFont(new Font("Times New Roman", Font.BOLD, (Dimensioni.WIDTH / 100) * 2));
        addMouseListener(this);
        indiceBottoni = i;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setForeground(Color.WHITE);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setForeground(Color.BLACK);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (indiceBottoni) {
            case 0 :
                MainPanel.finestraPrincipale.setContentPane(new PanelRandomGame());
                MainPanel.finestraPrincipale.revalidate();
                break;

            case 1 :
                MainPanel.finestraPrincipale.setContentPane(new PanelListGame());
                MainPanel.finestraPrincipale.revalidate();
                break;

            case 2 :
                MainPanel.finestraPrincipale.setContentPane(new PanelGenerateLevel());
                MainPanel.finestraPrincipale.revalidate();
                break;

            case 3 :
                int res = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler uscire dal gioco?", "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if(res == JOptionPane.YES_OPTION)
                    System.exit(0);
                break;
        }
    }
}
