package Fox;

import Matrix.Matrix;
public class Fox extends Thread {
    private final Matrix subMatrix1;
    private final Matrix subMatrix2;
    private final int i;
    private final int j;
    private final Matrix[][] resultMatrix;

    public Fox(Matrix subMatrix1, Matrix subMatrix2, int i, int j, Matrix[][] resultMatrix) {
        this.subMatrix1 = subMatrix1;
        this.subMatrix2 = subMatrix2;
        this.i = i;
        this.j = j;
        this.resultMatrix = resultMatrix;
    }

    @Override
    public void run() {
        resultMatrix[i][j] = resultMatrix[i][j].Add(subMatrix1.Multiply(subMatrix2));
    }
 }
