package Fox;

import Matrix.Matrix;

public class FoxMain {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix("Test Matrix A", 6, 4).generateData(11);
        matrix1.PrintSelf();

        Matrix matrix2 = new Matrix("Test Matrix B", 4, 4).generateData(11);
        matrix2.PrintSelf();

        var res = matrix1.Multiply(matrix2);
        res.PrintSelf();

        var resFox = FoxController.Multiply(matrix1, matrix2, 4, 2);
        resFox.PrintSelf();
    }
}
