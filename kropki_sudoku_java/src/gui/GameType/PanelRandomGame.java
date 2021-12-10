package gui.GameType;

import gui.Dimensioni.Dimensioni;

import javax.swing.*;
import java.awt.*;

public class PanelRandomGame extends JPanel {
    public PanelRandomGame(){
        this.setLayout(new BorderLayout());

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
        PanelComponentSup panelComponentSup=new PanelComponentSup();
        provaSup.add(panelComponentSup);

        PanelMatrix panelMatrix=new PanelMatrix();

        //provaSup.add(panelMatrix);

        this.add(provaSup,BorderLayout.NORTH);
        this.add(panelMatrix,BorderLayout.CENTER);



    }


}
