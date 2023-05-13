package Matrix;

import java.util.Random;

public class Matrix {
    private final Object mutex = new Object();
    private int[][] data;
    private String label;
    private int xSize;
    private int ySize;

    public Matrix(String label, int xSize, int ySize) {
        this.data = new int[xSize][ySize];
        this.xSize = xSize;
        this.ySize = ySize;
        this.label = label;
    }

    public Matrix generateData(int bound) {
        Random rand = new Random(0);
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                data[i][j] = rand.nextInt(bound);
            }
        }

        return this;
    }

    public void PrintSelf() {
        System.out.println(">>> " + label + " <<<");
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                System.out.printf("%4d|", data[i][j]);
            }
            System.out.println();
        }
    }

    public int getXSize() {
        return xSize;
    }

    public int getYSize() {
        return ySize;
    }

    public int getData(int x, int y) {
        return data[x][y];
    }

    public int[] getRow(int x) {
        return data[x];
    }

    public Matrix transpose() {
        Matrix transposedMatrix = new Matrix("Transposed " + this.label, ySize, xSize);
        for (var i = 0; i < xSize; i++) {
            for (var j = 0; j < ySize; j++) {
                transposedMatrix.setData(j, i, data[i][j]);
            }
        }
        return transposedMatrix;
    }

    public Matrix[][] Subdivide(int q) {
        if (xSize % q != 0 || ySize % q != 0) {
            throw new RuntimeException("Matrix can't be subdivided");
        }

        // q stands for count of submatrices in one row/column
        var newXSize = xSize / q;
        var newYSize = ySize / q;

        var result = new Matrix[q][q];
        for (var i = 0; i < q; i++) {
            for (var j = 0; j < q; j++) {
                result[i][j] = new Matrix("Submatrix " + i + " " + j, newXSize, newYSize);
                for (var k = 0; k < newXSize; k++) {
                    for (var l = 0; l < newYSize; l++) {
                        result[i][j].setData(k, l, data[i * newXSize + k][j * newYSize + l]);
                    }
                }
            }
        }

        return result;
    }

    public static Matrix Combine(Matrix[][] subdivided, int xSize, int ySize){
        var combined = new Matrix("Combined", xSize*subdivided.length, ySize*subdivided[0].length);
        for (var i = 0; i < subdivided.length; i++){
            for (var j = 0; j < subdivided[i].length; j++){
                for (var k = 0; k < xSize; k++){
                    for (var l = 0; l < ySize; l++){
                        combined.setData(i*xSize + k, j*ySize + l, subdivided[i][j].getData(k, l));
                    }
                }
            }
        }
        return combined;
    }

    public Matrix Multiply(Matrix multiplyMatrix) {
        if (this.getYSize() != multiplyMatrix.getXSize()) {
            throw new RuntimeException("Matrixes can't be multiplied");
        }

        var result = new Matrix("Result", xSize, multiplyMatrix.getYSize());
        for (var i = 0; i < xSize; i++) {
            for (var j = 0; j < multiplyMatrix.getYSize(); j++) {
                var sum = 0;
                for (var k = 0; k < ySize; k++) {
                    sum += data[i][k] * multiplyMatrix.getData(k, j);
                }
                result.setData(i, j, sum);
            }
        }

        return result;
    }

    public Matrix Add(Matrix addMatrix) {
        if (this.getXSize() != addMatrix.getXSize() || this.getYSize() != addMatrix.getYSize()) {
            throw new RuntimeException("Matrixes can't be added");
        }

        var result = new Matrix("Result", xSize, ySize);
        synchronized (mutex) {
            for (var i = 0; i < xSize; i++) {
                for (var j = 0; j < ySize; j++) {
                    result.setData(i, j, data[i][j] + addMatrix.getData(i, j));
                }
            }
        }

        return result;
    }

    public void setData(int x, int y, int value) {
        data[x][y] = value;
    }
}
