package gui.GameType;

import gui.Dimensioni.Dimensioni;
import model.KropkiSudoku;

import javax.swing.*;
import java.awt.*;

public class PanelRandomGame extends JPanel {

    private KropkiSudoku kropkiSudoku;

    public PanelRandomGame(KropkiSudoku kropkiSudoku){

        this.setLayout(new BorderLayout());
        this.kropkiSudoku=kropkiSudoku;
        JPanel provaSup=new JPanel();
        provaSup.setLayout(new BoxLayout(provaSup,BoxLayout.PAGE_AXIS));

        JPanel supporto=new JPanel();
        supporto.setBackground(Color.RED);
        supporto.setLayout(new BorderLayout());

        JLabel label=new JLabel("Scritta superiore");
        label.setFont(new Font("Arial",Font.BOLD,20));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        supporto.add(label, BorderLayout.CENTER);

        provaSup.add(supporto);
        PanelComponentSup panelComponentSup=new PanelComponentSup(kropkiSudoku);
        provaSup.add(panelComponentSup);

        PanelMatrix panelMatrix=new PanelMatrix(kropkiSudoku.getInitialMatrix(),kropkiSudoku.getPoints(),kropkiSudoku.getN());


        //provaSup.add(panelMatrix);

        this.add(provaSup,BorderLayout.NORTH);
        this.add(panelMatrix,BorderLayout.CENTER);
    }


}
