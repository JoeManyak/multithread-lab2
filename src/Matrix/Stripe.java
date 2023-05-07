package Matrix;

public class Stripe extends Thread {
    private Matrix matrix;

    private int firstRowIndex;
    private int lastRowIndex;

    private Matrix multiplyMatrix;
    private Matrix resultMatrix;

    public Stripe(Matrix matrix, Matrix multiplyMatrix, int firstRowIndex, int lastRowIndex, Matrix resultMatrix) {
        this.matrix = matrix;
        this.firstRowIndex = firstRowIndex;
        this.lastRowIndex = lastRowIndex;
        this.multiplyMatrix = multiplyMatrix;
        this.resultMatrix = resultMatrix;
    }

    @Override
    public void run() {
        for (int i = firstRowIndex; i <= lastRowIndex; i++) {
            for (int j = 0; j < multiplyMatrix.getYSize(); j++) {
                int result = 0;
                if (i==1 && j == 2){
                    System.out.println("Debug");
                }
                for (int k = 0; k < matrix.getYSize(); k++) {
                    var a = matrix.getData(i, k);
                    var b = multiplyMatrix.getData(k, j);
                    result +=  a * b;
                }
                resultMatrix.setData(i,j, result);
            }
        }
    }
}
