package gui.Menu;

import gui.Dimensioni.Dimensioni;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MenuPrincipale extends JPanel {

    private Image background;

    public MenuPrincipale(){
        try {
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream("menu.jpg"));
            this.setBackground(img);
            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            this.add(Box.createRigidArea(new Dimension(0,((Dimensioni.HEIGHT/100)*35))));
            for(int i=0; i <= 3; i++) {
                MenuBottoni button = new MenuBottoni(i);
                this.add(button);
                this.add(Box.createRigidArea(new Dimension(0,((Dimensioni.HEIGHT/100)*5))));
                button.setAlignmentX(Component.CENTER_ALIGNMENT);
            }
        }
        catch (IOException e) {
            System.out.println("Errore");
        }
    }
    public void setBackground(Image img)
    {
        if(img != null) {
            background = img;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        background = background.getScaledInstance(getSize().width,getSize().height, Image.SCALE_FAST);
        drawBackground(g);
    }

    public void drawBackground(Graphics g) {
        g.drawImage(background, 0, 0, null);
    }
}
