package gui.GameType;

import gui.Dimensioni.Dimensioni;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class RoundButton extends JButton {

    public RoundButton(String label) {
        super(label);
        this.setEnabled(false);
        //setBackground(Color.lightGray);
        setFocusable(false);

        //Dimension size = getPreferredSize();
        /*
        Dimension size = new Dimension(10,10);
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
        setSize(size);
        */

        setContentAreaFilled(false);
    }

    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.gray);
        } else {
            g.setColor(getBackground());
        }
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);

        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(Color.darkGray);
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }

    // Hit detection.
    Shape shape;

    public boolean contains(int x, int y) {
        // If the button has changed size,  make a new shape object.
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }
}