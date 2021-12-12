package gui.GameType;

import gui.Dimensioni.Dimensioni;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ComponentMatrix extends JPanel {

    private JTextField numero;
    private RoundButton cerchioDestra;
    private RoundButton cerchioSotto;


    public ComponentMatrix(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        numero=new JTextField();

        numero.setDocument(new LengthRestrict(1));
        numero.setHorizontalAlignment(JTextField.CENTER);
        numero.setPreferredSize(new Dimension((Dimensioni.WIDTH/100)*5, (Dimensioni.HEIGHT/100)*5));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        this.add(numero, c);

        cerchioDestra=new RoundButton("");
        cerchioDestra.setPreferredSize(new Dimension(((Dimensioni.WIDTH/100)*2)-2, ((Dimensioni.HEIGHT/100)*2)-2));
        c.gridx = 1;
        c.gridy = 0;
        this.add(cerchioDestra, c);

        JPanel supporto=new JPanel();
        cerchioSotto=new RoundButton("");
        cerchioSotto.setPreferredSize(new Dimension(((Dimensioni.WIDTH/100)*2)-2, ((Dimensioni.HEIGHT/100)*2)-2));
        c.gridx = 0;
        c.gridy = 1;
        supporto.add(cerchioSotto);
        this.add(supporto,c);

        numero.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println();

                try {
                    int numero=Integer.parseInt(e.getKeyChar()+"");
                    System.out.println(numero);
                }
                catch (Exception ex){

                }

            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

    }

    public void setBackgroundCerchioDestra(Color c){
        cerchioDestra.setBackground(c);
    }

    public void setBackgroundCerchioSotto(Color c){
        cerchioSotto.setBackground(c);
    }

    public void settaInvisibileDestra(){
        cerchioDestra.setVisible(false);
    }
    public void settaInvisibileSotto(){
        cerchioSotto.setVisible(false);
    }

    public void settaNumero(String str){
        numero.setText(str);
    }

    public void settaBorderNull(){
        cerchioDestra.setDestroy(true);
        cerchioDestra.setBorderPainted(false);
        cerchioDestra.setContentAreaFilled(false);
        cerchioDestra.setFocusPainted(false);
        cerchioDestra.repaint();
        cerchioDestra.revalidate();
    }
}
