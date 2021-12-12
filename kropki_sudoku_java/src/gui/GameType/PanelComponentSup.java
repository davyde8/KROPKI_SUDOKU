package gui.GameType;

import gui.Dimensioni.Dimensioni;
import gui.ListenerButtonSup.ListenerButtonSup;
import model.KropkiSudoku;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PanelComponentSup extends JPanel {

    String path="/gui/GameType/";
    private JButton check;
    private JButton hint;
    private JButton solve;

    public PanelComponentSup(KropkiSudoku kropki){
        GameManager.getInstance().setKropki(kropki);
        GameManager.getInstance().setUserInput(kropki.getInitialMatrix());
        this.setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
        Component rigidArea = Box. createRigidArea(new Dimension(0, (Dimensioni.HEIGHT/100)*20 ));
        this.add(rigidArea);
        check = impostaBottone("check.png");
        ListenerButtonSup checkListener=new ListenerButtonSup(1);
        check.addMouseListener(checkListener);
        this.add(check);
        rigidArea = Box. createRigidArea(new Dimension((Dimensioni.WIDTH/100)*5,0 ));
        this.add(rigidArea);
        hint = impostaBottone("Hint.png");
        ListenerButtonSup hintListener=new ListenerButtonSup(2);
        hint.addMouseListener(hintListener);
        this.add(hint);
        rigidArea = Box. createRigidArea(new Dimension((Dimensioni.WIDTH/100)*5,0 ));
        this.add(rigidArea);
        solve=impostaBottone("solve.png");
        ListenerButtonSup solveListener=new ListenerButtonSup(3);
        solve.addMouseListener(solveListener);
        this.add(solve);
    }

    public ImageIcon getIcona (String nameImg) {
        Image scalata=null;
        ImageIcon icona=null;
        try {
            BufferedImage legge = ImageIO.read(getClass().getResourceAsStream(path+nameImg));
            scalata = legge.getScaledInstance((Dimensioni.WIDTH/100)*15,(Dimensioni.HEIGHT/100)*7,Image.SCALE_SMOOTH);
            icona = new ImageIcon (scalata);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return icona;
    }

    public JButton impostaBottone(String icona) {
        ImageIcon icon = getIcona(icona);
        JButton b = new JButton (icon);
        b.setBorder(null);
        b.setContentAreaFilled(false);
        return b;
    }
}
