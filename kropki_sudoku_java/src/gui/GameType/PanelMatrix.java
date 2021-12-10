package gui.GameType;

import javax.swing.*;
import java.awt.*;

public class PanelMatrix extends JPanel {

    private int nDimensionMatrix;
    private ComponentMatrix [][]matrix;

    public PanelMatrix(){

        nDimensionMatrix=3;
        matrix = new ComponentMatrix[nDimensionMatrix][nDimensionMatrix];
        ComponentMatrix f1=new ComponentMatrix();
        matrix[0][0]=f1;
        ComponentMatrix f2=new ComponentMatrix();
        matrix[0][1]=f2;
        ComponentMatrix f3=new ComponentMatrix();
        matrix[0][2]=f3;
        ComponentMatrix f4=new ComponentMatrix();
        matrix[1][0]=f4;
        ComponentMatrix f5=new ComponentMatrix();
        matrix[1][1]=f5;
        ComponentMatrix f6=new ComponentMatrix();
        matrix[1][2]=f6;
        ComponentMatrix f7=new ComponentMatrix();
        matrix[2][0]=f7;
        ComponentMatrix f8=new ComponentMatrix();
        matrix[2][1]=f8;
        ComponentMatrix f9=new ComponentMatrix();
        matrix[2][2]=f9;



        GridLayout griglia=new GridLayout(nDimensionMatrix,nDimensionMatrix,-50,0);
        this.setLayout(griglia);
        for(int i=0;i<nDimensionMatrix;i++){
            for(int j=0;j<nDimensionMatrix;j++){
                this.add(matrix[i][j]);
            }
        }
/*
        matrix[0][2].setText("4");

        for(int i=0;i<nDimensionMatrix;i++){
            for(int j=0;j<nDimensionMatrix;j++){
                System.out.println(matrix[i][j].getText());
            }
            System.out.println();
        }
*/

    }


}
