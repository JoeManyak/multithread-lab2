package Matrix;

import java.util.Random;

public class Matrix {
    private int[][] data;
    private String label;
    private int xSize;
    private int ySize;

    public Matrix(String label, int xSize, int ySize) {
        this.data = new int[xSize][ySize];
        this.xSize = xSize;
        this.ySize = ySize;
        this.label = label;

        Random rand = new Random(0);
        for (int i = 0; i < xSize; i++){
            for (int j = 0; j < ySize; j++){
                data[i][j] = rand.nextInt(11);
            }
        }
    }

    public void PrintSelf(){
        System.out.println(">>> "+label+" <<<");
        for (int i = 0; i < xSize; i++){
            for (int j = 0; j < ySize; j++){
                System.out.printf("%4d|",data[i][j]);
            }
            System.out.println();
        }
    }

    public int getXSize(){
        return xSize;
    }

    public int getYSize(){
        return ySize;
    }

    public int getData(int x, int y){
        return data[x][y];
    }

    public void setData(int x, int y, int value){
        data[x][y] = value;
    }
}
