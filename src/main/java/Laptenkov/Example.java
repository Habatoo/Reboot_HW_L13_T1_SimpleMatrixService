package Laptenkov;

//import org.example.service.MatrixService;

/**
 * Example
 */
public class Example {
    public static void main(String[] args) throws InterruptedException {

        MatrixService matrixService = new MatrixService();

        int[][] matrix = new int[][]{
                {1, 1},
                {2, 2},
                {3, 3},
                {4, 4},
                {10, 10}
        };

        int sum = matrixService.sum(matrix, 1);
        System.out.println("sum = " + sum);
//        Assert.assertEquals(40, sum);

        sum = matrixService.sum(matrix, 2);
        System.out.println("sum = " + sum);
//        Assert.assertEquals(40, sum);

        matrix = new int[][]{
                {1, -1, 1},
                {2, -2, 2},
                {-3, 3, 3},
                {-4, 4, 4},
                {-10, 11, 5}
        };
        sum = matrixService.sum(matrix, 2);
        System.out.println("sum = " + sum);
//      Assert.assertEquals(40, sum);
    }
}