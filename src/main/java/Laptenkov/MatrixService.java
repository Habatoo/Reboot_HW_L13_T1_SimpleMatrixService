package Laptenkov;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Реализовать метод sum в классе MatrixService.
 * Данный метод находит сумму элементов в двумерном массиве.
 * Метод sum позволяет вычислять сумму с использованием nthreads потоков.
 * За счет этого достигается повышение производительности.
 *
 * Сигнатура метода:
 * int sum(int[][] matrix, int nthreads)
 * Метод sum декомпозирует работу путем запуска задач ColumnSummator,
 * которые реализуют интерфейс Runnable Задача ColumnSummator
 * суммирует элементы матрицы в определенном диапазоне столбцов.
 *
 * Критерии приемки:
 *     Для сихронизации потоков использовать wait - notify
 *     Метод sum должен быть покрыт unit тестами.
 */
public class MatrixService {

    /**
     * Iterates over each column and calculates sum of elements
     */
    private static class ColumnSummator implements Runnable {

        private int fromColumn;
        private int toColumn;
        private int[][] matrix;
        private int resultId;
        private int[] result;
        private AtomicInteger syncObject;

        /**
         * Constructor
         *
         * @param fromColumn - column index start with
         * @param toColumn   - to column index. You should process columns strong before column with index toColumn
         * @param matrix     - matrix
         * @param resultId   - position of result in result array
         * @param result     - result array
         * @param syncObject - object for synchronization between threads
         */
        public ColumnSummator(int fromColumn, int toColumn, int[][] matrix, int resultId, int[] result, AtomicInteger syncObject) {
            // should be implemented
        }

        @Override
        public void run() {
            // should be implemented
        }
    }

    /**
     * Get sum of matrix elements. You should parallel work between several threads
     *
     * @param matrix   - matrix
     * @param nthreads - threads count. It is guarantee that number of matrix column is greater than nthreads.
     * @return sum of matrix elements
     */
    public int sum(int[][] matrix, int nthreads) throws InterruptedException {

        int[] result = new int[nthreads];
        AtomicInteger syncObject = new AtomicInteger(nthreads);

        // create threads and divide work between them
        // should be implemented

        int sum = 0;
        for (int threadResult : result) {
            sum += threadResult;
        }
        return sum;
    }
}