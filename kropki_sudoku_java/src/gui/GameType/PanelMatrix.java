package gui.GameType;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelMatrix extends JPanel {

    private int nDimensionMatrix;
    private ComponentMatrix [][]matrix;

    public PanelMatrix(int [][] matrixBack, List<Integer[]> points,int nDimensionMatrix){
        this.nDimensionMatrix=nDimensionMatrix;
        this.setBackground(Color.WHITE);

        matrix = new ComponentMatrix[nDimensionMatrix][nDimensionMatrix];
        for(int i=0;i<nDimensionMatrix;i++){
            for(int j=0;j<nDimensionMatrix;j++){
                ComponentMatrix tmp=new ComponentMatrix(i,j);
                tmp.settaNumero(String.valueOf(matrixBack[i][j]));
                tmp.setBackgroundCerchioDestra(findPoint(points,i,j,true));
                tmp.setBackgroundCerchioSotto(findPoint(points,i,j,false));
                matrix[i][j]= tmp;
            }
        }

        GridBagConstraints griglia = new GridBagConstraints();
        griglia.fill = GridBagConstraints.HORIZONTAL;
        griglia.weightx = 0;

        this.setLayout(new GridBagLayout());
        for(int i=0;i<nDimensionMatrix;i++){
            for(int j=0;j<nDimensionMatrix;j++){
                if(i==nDimensionMatrix-1){
                    matrix[j][i].setBackgroundCerchioSotto(Color.RED);
                    matrix[j][i].settaInvisibileSotto();
                }
                if(j==nDimensionMatrix-1){
                    matrix[j][i].setBackgroundCerchioDestra(Color.BLUE);
                    matrix[j][i].settaInvisibileDestra();
                }
                griglia.gridx = i;
                griglia.gridy = j;
                this.add(matrix[i][j],griglia);
            }
        }
    }

    //trova il colore per il pallino
    //true cerchio destro
    //false cerchio sotto
    //se non trova nulla ritorna null
    public Color findPoint(List<Integer[]> points,int i,int j,boolean flag){
        for(Integer[] point: points){
            if(point[0]==j+1 && point[1]==i+1 && !flag && point[2]==j+2 && point[3]==i+1){
                return point[4]==0 ? Color.RED : Color.BLUE;
            }
            if(point[0]==j+1 && point[1]==i+1 && flag && point[2]==j+1 && point[3]==i+2){
                return point[4]==0 ? Color.RED : Color.BLUE;
            }
        }
        return null;
    }
}
