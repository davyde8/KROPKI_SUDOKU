package gui.GameType;

import gui.Dimensioni.Dimensioni;
import model.KropkiSudoku;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PanelRandomGame extends JPanel {

    private KropkiSudoku kropkiSudoku;

    public PanelRandomGame(KropkiSudoku kropkiSudoku){

        this.setLayout(new BorderLayout());
        this.kropkiSudoku=kropkiSudoku;
        JPanel provaSup=new JPanel();
        provaSup.setLayout(new BoxLayout(provaSup,BoxLayout.PAGE_AXIS));

        JPanel supporto=new JPanel();
        supporto.setLayout(new BorderLayout());

        JLabel label=new JLabel("KROPKI SUDOKU");
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT,
                    new File("resources/fonts/IndieFlower-Regular.ttf")).deriveFont(48f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            label.setFont(customFont);
        } catch (IOException |FontFormatException e) {
            label.setFont(new Font("Srial",Font.BOLD,25));
        }
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        supporto.add(label, BorderLayout.CENTER);
        provaSup.add(this.add(Box.createRigidArea(new Dimension(0,((Dimensioni.HEIGHT/100)*1)))));
        provaSup.add(supporto);
        PanelComponentSup panelComponentSup=new PanelComponentSup(kropkiSudoku);
        provaSup.add(panelComponentSup);

        PanelMatrix panelMatrix=new PanelMatrix(kropkiSudoku.getInitialMatrix(),kropkiSudoku.getPoints(),kropkiSudoku.getN());


        //provaSup.add(panelMatrix);

        this.add(provaSup,BorderLayout.NORTH);
        this.add(panelMatrix,BorderLayout.CENTER);
    }


}
