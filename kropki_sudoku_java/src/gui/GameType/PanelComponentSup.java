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
    private JButton quit;
    private JButton menu;
    private JButton info;

    public PanelComponentSup(KropkiSudoku kropki){
        GameManager.getInstance().setKropki(kropki);
        GameManager.getInstance().setUserInput(kropki.getInitialMatrix());
        this.setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
        Component rigidArea = Box. createRigidArea(new Dimension(0, (Dimensioni.HEIGHT/100)*10 ));
        this.add(rigidArea);
        menu = impostaBottone("menu_button.png");
        ListenerButtonSup menuListener=new ListenerButtonSup(5);
        menu.addMouseListener(menuListener);
        this.add(menu);
        rigidArea = Box. createRigidArea(new Dimension((Dimensioni.WIDTH/100)*4,0 ));
        this.add(rigidArea);
        check = impostaBottone("check.png");
        ListenerButtonSup checkListener=new ListenerButtonSup(1);
        check.addMouseListener(checkListener);
        this.add(check);
        rigidArea = Box. createRigidArea(new Dimension((Dimensioni.WIDTH/100)*4,0 ));
        this.add(rigidArea);
        hint = impostaBottone("Hint.png");
        ListenerButtonSup hintListener=new ListenerButtonSup(2);
        hint.addMouseListener(hintListener);
        this.add(hint);
        rigidArea = Box. createRigidArea(new Dimension((Dimensioni.WIDTH/100)*4,0 ));
        this.add(rigidArea);
        solve=impostaBottone("solve.png");
        ListenerButtonSup solveListener=new ListenerButtonSup(3);
        solve.addMouseListener(solveListener);
        this.add(solve);
        rigidArea = Box. createRigidArea(new Dimension((Dimensioni.WIDTH/100)*4,0 ));
        this.add(rigidArea);
        quit = impostaBottone("quit.png");
        ListenerButtonSup quitListener=new ListenerButtonSup(4);
        quit.addMouseListener(quitListener);
        this.add(quit);
        rigidArea = Box. createRigidArea(new Dimension((Dimensioni.WIDTH/100)*4,0 ));
        this.add(rigidArea);
        info = impostaBottone("info.png");
        ListenerButtonSup infoList=new ListenerButtonSup(6);
        info.setPreferredSize(new Dimension((Dimensioni.WIDTH/100)*6,(Dimensioni.WIDTH/100)*6));
        info.setMinimumSize(new Dimension((Dimensioni.WIDTH/100)*6,(Dimensioni.WIDTH/100)*6));
        info.setMaximumSize(new Dimension((Dimensioni.WIDTH/100)*6,(Dimensioni.WIDTH/100)*6));

        info.addMouseListener(infoList);
        this.add(info);

    }

    public ImageIcon getIcona (String nameImg) {
        Image scalata=null;
        ImageIcon icona=null;
        try {
            BufferedImage legge = ImageIO.read(getClass().getResourceAsStream(path+nameImg));
            scalata = legge.getScaledInstance((Dimensioni.WIDTH/100)*15,(Dimensioni.HEIGHT/100)*12,Image.SCALE_SMOOTH);
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
