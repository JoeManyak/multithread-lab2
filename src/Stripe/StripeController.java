package Stripe;

import Matrix.Matrix;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StripeController {
    public static Matrix Multiply(Matrix matrix1, Matrix matrix2, int threadPoolSize) throws InterruptedException {
        if (matrix1.getYSize() != matrix2.getXSize()) {
            throw new RuntimeException("Matrixes can't be multiplied");
        }

        ExecutorService pool =  Executors.newFixedThreadPool(threadPoolSize);
        ArrayList<Callable<Object>> tasks = new ArrayList<>();

        Matrix transposedMatrix2 = matrix2.transpose();
        Matrix resultMatrix = new Matrix("Result", matrix1.getXSize(), matrix2.getYSize());

        for (var i = 0; i< matrix1.getXSize(); i++){
            var matrix1Row = matrix1.getRow(i);
            for (var j = 0; j < transposedMatrix2.getXSize(); j++){
                tasks.add(Executors.callable(new Stripe(matrix1Row, transposedMatrix2.getRow(j), i, j, resultMatrix)));
            }
        }

        pool.invokeAll(tasks);

        return resultMatrix;
    }
}
