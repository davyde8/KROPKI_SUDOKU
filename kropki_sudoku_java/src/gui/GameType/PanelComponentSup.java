package gui.GameType;

import gui.Dimensioni.Dimensioni;

import javax.swing.*;
import java.awt.*;

public class PanelComponentSup extends JPanel {
    public PanelComponentSup(){
        this.setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
        Component rigidArea = Box. createRigidArea(new Dimension(0, (Dimensioni.HEIGHT/100)*20 ));
        this.add(rigidArea);
        JButton check=new JButton("CHECK");
        this.add(check);
        rigidArea = Box. createRigidArea(new Dimension((Dimensioni.WIDTH/100)*5,0 ));
        this.add(rigidArea);
        JButton hint=new JButton("HINT");
        this.add(hint);
        rigidArea = Box. createRigidArea(new Dimension((Dimensioni.WIDTH/100)*5,0 ));
        this.add(rigidArea);
        JButton solve=new JButton("SOLVE");
        this.add(solve);
    }
}
