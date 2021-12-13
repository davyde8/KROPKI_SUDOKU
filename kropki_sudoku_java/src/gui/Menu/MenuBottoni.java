package gui.Menu;

import gui.Dimensioni.Dimensioni;
import gui.GameType.GameManager;
import gui.GameType.PanelGenerateLevel;
import gui.GameType.PanelListGame;
import gui.Main.MainPanel;
import gui.GameType.PanelRandomGame;
import model.InstanceGenerator;
import model.KropkiSudoku;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.regex.Pattern;

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
        Border border = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK);
        this.setBorder(border);
        this.setMaximumSize(new Dimension(((Dimensioni.WIDTH / 100) * 30), ((Dimensioni.HEIGHT / 100) * 8)));
        this.setBackground(new Color(103,187,234));
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
                MainPanel.finestraPrincipale.setContentPane(new PanelRandomGame(new KropkiSudoku()));
                MainPanel.finestraPrincipale.revalidate();
                break;

            case 1 :
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }catch(Exception ex) {
                    ex.printStackTrace();
                }
                String f="";
                while(!f.contains("kropki_sudoku_data")) {
                    JFileChooser fileChooser = new JFileChooser("resources/data");
                    int n = fileChooser.showOpenDialog(MainPanel.finestraPrincipale);
                    f = fileChooser.getSelectedFile().getName();
                    if(!f.contains("kropki_sudoku_data")){
                        JOptionPane.showMessageDialog(this,"The selected file is not valid!!");
                    }
                }

                String id=f.replace("kropki_sudoku_data_","").replace(".dzn","");
                System.out.println(id);

                MainPanel.finestraPrincipale.setContentPane(new PanelListGame(new KropkiSudoku(id)));
                MainPanel.finestraPrincipale.revalidate();
                break;

            case 2 :
                String generated = InstanceGenerator.instanceGenerator();
                JOptionPane.showMessageDialog(MainPanel.finestraPrincipale, "Generata Istanza: "+generated);
                break;
            case 3 :
                int res = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler uscire dal gioco?", "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if(res == JOptionPane.YES_OPTION)
                    System.exit(0);
                break;
        }
    }
}
