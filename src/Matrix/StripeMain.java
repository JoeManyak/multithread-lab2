package Matrix;

public class StripeMain {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix("Test Matrix A", 5, 3);
        matrix1.PrintSelf();
        Matrix matrix2 = new Matrix("Test Matrix B", 3, 4);
        matrix2.PrintSelf();


        Matrix result = StripeController.Multiply(matrix1, matrix2, 2);
        result.PrintSelf();
    }
}
