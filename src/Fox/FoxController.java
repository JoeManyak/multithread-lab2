package Fox;

import Matrix.Matrix;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FoxController {
    public static Matrix Multiply(Matrix matrix1, Matrix matrix2, int threadPoolSize, int q) {
        if (matrix1.getYSize() != matrix2.getXSize()) {
            throw new RuntimeException("Matrixes can't be multiplied");
        }

        var subdividedMatrix1 = matrix1.Subdivide(q);
        var subdividedMatrix2 = matrix2.Subdivide(q);
        var result = new Matrix("Result", matrix1.getXSize(), matrix2.getYSize());
        var resSubdivided = result.Subdivide(q);

        ExecutorService pool =  Executors.newFixedThreadPool(threadPoolSize);
        ArrayList<Callable<Object>> tasks = new ArrayList<>();
        for (var i = 0; i < resSubdivided.length; i++){
            for (var j = 0; j < resSubdivided[i].length; j++){
                for (var s = 0; s < q; s++){
                    tasks.add(Executors.callable(new Fox(
                            subdividedMatrix1[i][s],
                            subdividedMatrix2[s][j],
                            i,
                            j,
                            resSubdivided)));
                }
            }
        }
        System.out.println("Tasks count: " + tasks.size());
        try {
            pool.invokeAll(tasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return Matrix.Combine(resSubdivided, resSubdivided[0][0].getXSize(), resSubdivided[0][0].getYSize());
    }
}
