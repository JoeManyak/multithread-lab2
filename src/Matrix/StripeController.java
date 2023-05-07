package Matrix;

public class StripeController {
    public static Matrix Multiply(Matrix matrix1, Matrix matrix2, int stripesCount) {
        var resultMatrix = new Matrix("Result Matrix", matrix1.getXSize(), matrix2.getYSize());
        var matrix1XSize = matrix1.getXSize();
        matrix1XSize += matrix1XSize % stripesCount;
        var stripeSize = matrix1XSize / stripesCount;
        if (matrix1.getYSize() != matrix2.getXSize()) {
            throw new RuntimeException("Matrixes can't be multiplied");
        }

        for (int i = 0; i < stripesCount; i++) {
            var left = i * stripeSize;
            var right = (i + 1) * stripeSize - 1;
            if (right >= matrix1.getXSize()) {
                right = matrix1.getXSize() - 1;
            }

            var stripe = new Stripe(matrix1, matrix2, left , right, resultMatrix);
            stripe.start();
            try {
                stripe.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return resultMatrix;
    }
}
