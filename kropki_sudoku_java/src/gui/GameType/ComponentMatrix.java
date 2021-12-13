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
    private int coordinataI;
    private int coordinataJ;

    public ComponentMatrix(int i,int j){
        this.coordinataI=i;
        this.coordinataJ=j;

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
                try {
                    System.out.println(e.getKeyChar()+"");
                    if(!(e.getKeyChar()+""=="")) {
                        int numeroLetto = Integer.parseInt(e.getKeyChar() + "");
                        GameManager.getInstance().getUserInput()[j][i] = numeroLetto;
                    }
                }
                catch (Exception ex){
                    ex.printStackTrace();
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

    public void settaInvisibileDestra(){
        this.cerchioDestra.setVisible(false);
    }

    public void settaInvisibileSotto(){
        this.cerchioSotto.setVisible(false);
    }

    public void setBackgroundCerchioDestra(Color c){
        if(c!=null) {
            cerchioDestra.setBackground(c);
        }
        else{
            settaBorderNull(true);
        }
    }

    public void setBackgroundCerchioSotto(Color c){
        if(c!=null) {
            cerchioSotto.setBackground(c);
        }
        else{
            settaBorderNull(false);
        }
    }

    public void settaNumero(String str){
        numero.setText(str);
    }

    public void settaBorderNull(boolean direzione){
        RoundButton tmp;
        if(direzione==true){
            tmp=cerchioDestra;
        }
        else{
            tmp=cerchioSotto;
        }
        tmp.setDestroy(true);
        tmp.setBorderPainted(false);
        tmp.setContentAreaFilled(false);
        tmp.setFocusPainted(false);
        tmp.repaint();
        tmp.revalidate();
    }

    public void setTextColor(Color color) {
        this.numero.setBorder(BorderFactory.createLineBorder(color));
    }
}
