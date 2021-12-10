package gui.GameType;

import gui.Dimensioni.Dimensioni;

import javax.swing.*;
import java.awt.*;

public class ComponentMatrix extends JPanel {

    private JTextField numero;
    private JButton cerchioDestra;
    private JButton cerchioSotto;


    public ComponentMatrix(){
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.BLUE);
        GridBagConstraints c = new GridBagConstraints();

        numero=new JTextField();
        numero.setPreferredSize(new Dimension(20, 50));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0;
        c.gridx = 0;
        c.gridy = 0;
        this.add(numero, c);

        cerchioDestra=new RoundButton("");
        cerchioDestra.setPreferredSize(new Dimension(10, 10));
        c.weightx = -5;
        c.gridx = 1;
        c.gridy = 0;
        this.add(cerchioDestra, c);
    }

        /*
        numero=new JTextField();

        cerchioDestra=new RoundButton("");
        cerchioSotto=new RoundButton("");

        cerchioDestra.setBackground(Color.RED);
        cerchioSotto.setBackground(Color.BLUE);

        GridLayout griglia=new GridLayout(2,2);
        this.setLayout(griglia);

        this.add(numero);
        this.add(cerchioDestra);
        this.add(cerchioSotto);
        this.add(Box.createRigidArea(new Dimension((Dimensioni.WIDTH/150)*15,(Dimensioni.HEIGHT/150)*15)));
        */
}
