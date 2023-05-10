package Stripe;

import Matrix.Matrix;

public class Stripe extends Thread {
    private int[] matrix1Row;
    private int matrix1RowIndex;
    private int[] matrix2Col;
    private int matrix2ColIndex;
    private Matrix resultMatrix;

    public Stripe(int[] matrix1Row, int[] matrix2Col, int matrix1RowIndex, int matrix2ColIndex, Matrix resultMatrix) {
        this.matrix1Row = matrix1Row;
        this.matrix2Col = matrix2Col;
        this.matrix1RowIndex = matrix1RowIndex;
        this.matrix2ColIndex = matrix2ColIndex;
        this.resultMatrix = resultMatrix;
    }

    @Override
    public void run() {
        // matrix1Row.length == matrix2Col.length
        var sum = 0;
        for (int i = 0; i < matrix1Row.length; i++) {
            sum += matrix1Row[i] * matrix2Col[i];
        }
        resultMatrix.setData(matrix1RowIndex, matrix2ColIndex, sum);
    }
}
