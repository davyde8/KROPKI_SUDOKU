package gui.GameType;

import javax.swing.*;
import java.awt.*;

public class PanelMatrix extends JPanel {

    private int nDimensionMatrix;
    private ComponentMatrix [][]matrix;

    public PanelMatrix(){
        this.setBackground(Color.WHITE);
        nDimensionMatrix=9;
        matrix = new ComponentMatrix[nDimensionMatrix][nDimensionMatrix];
        for(int i=0;i<nDimensionMatrix;i++){
            for(int j=0;j<nDimensionMatrix;j++){
                matrix[i][j]= new ComponentMatrix();
            }
        }



        GridBagConstraints griglia = new GridBagConstraints();
        griglia.fill = GridBagConstraints.HORIZONTAL;
        griglia.weightx = 0;

        this.setLayout(new GridBagLayout());
        int cont=0;
        for(int i=0;i<nDimensionMatrix;i++){
            for(int j=0;j<nDimensionMatrix;j++){
                cont++;
                matrix[i][j].settaNumero(String.valueOf(cont));
                if(i==nDimensionMatrix-1){
                    matrix[j][i].setBackgroundCerchioSotto(Color.RED);
                    matrix[j][i].settaInvisibileSotto();
                    //matrix[j][i].setBackgroundCerchioDestra(Color.RED);
                    //System.out.println("secondoIf"+i+" "+j);
                }
                if(j==nDimensionMatrix-1){
                    matrix[j][i].setBackgroundCerchioDestra(Color.BLUE);
                    matrix[j][i].settaInvisibileDestra();
                    //matrix[j][i].setBackgroundCerchioDestra(Color.RED);
                    //System.out.println("secondoIf"+i+" "+j);
                }
                griglia.gridx = i;
                griglia.gridy = j;
                this.add(matrix[i][j],griglia);

            }
        }

        for(int i=0;i<nDimensionMatrix;i++) {
            for (int j = 0; j < nDimensionMatrix; j++) {
                if(j==0 && i==0){
                    matrix[j][i].settaBorderNull();
                }
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
