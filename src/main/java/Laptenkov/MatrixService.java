package Laptenkov;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс {@link MatrixService} для суммирования элементов
 * матрицы.
 *
 * @author habatoo.
 * <p>
 * Критерии приемки:
 * Для сихронизации потоков использовать wait - notify
 * Метод sum должен быть покрыт unit тестами.
 */
public class MatrixService {

    /**
     * Внутрений класс {@link ColumnSummator}
     * для итерирования по колонкам переданной матрицы
     * и расчета суммы элементов.
     */
    private static class ColumnSummator implements Runnable {

        private int fromColumn;
        private int toColumn;
        private int[][] matrix;
        private int resultId;
        private int[] result;
        private AtomicInteger syncObject;

        /**
         * Конструктор объекта {@link ColumnSummator} с полным перечнем
         * параметров.
         *
         * @param fromColumn стартовая колонка матрицы, с которой начинается суммирование
         * @param toColumn   конечная колонка матрицы, на которой заканчивается суммирование,
         *                   суммирование продолжается строго после колонки с индексом toColumn
         * @param matrix     матрица для суммирования
         * @param resultId   индекс позиции суммы в результирующей матрице
         * @param result     результирующая матрица
         * @param syncObject объект {@link AtomicInteger} для синхронизации между потоками
         */
        public ColumnSummator(int fromColumn,
                              int toColumn,
                              int[][] matrix,
                              int resultId,
                              int[] result,
                              AtomicInteger syncObject) {
            this.fromColumn = fromColumn;
            this.toColumn = toColumn;
            this.matrix = matrix;
            this.resultId = resultId;
            this.result = result;
            this.syncObject = syncObject;
        }

        /**
         * Метод {@link ColumnSummator#run()} объекта {@link ColumnSummator}
         * реализует логику суммирования элементов двумерной марицы.
         */
        @Override
        public void run() {
            for (; fromColumn <= toColumn; fromColumn++) {
                for (int j = 0; j < matrix.length; j++) {
                    result[resultId] += matrix[j][fromColumn];
                }
            }

            synchronized (syncObject) {
                syncObject.decrementAndGet();
                if (syncObject.get() == 0) {
                    syncObject.notify();
                }
            }
        }
    }

    /**
     * Метод {@link ColumnSummator#sum(int[][], int)} объекта {@link ColumnSummator}
     * находит сумму элементов в двумерном массиве.
     * Метод sum позволяет вычислять сумму с использованием nthreads потоков.
     * За счет этого достигается повышение производительности.
     * Сигнатура метода:
     * int sum(int[][] matrix, int nthreads)
     * Метод sum декомпозирует работу путем запуска задач ColumnSummator,
     * которые реализуют интерфейс Runnable Задача ColumnSummator
     * суммирует элементы матрицы в определенном диапазоне столбцов.
     *
     * @param matrix   матрица для суммирования.
     * @param nthreads количество потоков (гарантированно, что число колонок в матрице больше числа потоков).
     * @return сумма элементов матрицы matrix.
     */
    public int sum(int[][] matrix, int nthreads) throws InterruptedException {

        int[] result = new int[nthreads];
        AtomicInteger syncObject = new AtomicInteger(nthreads);
        List<Thread> threadList = new ArrayList<>();

        if (matrix.length == 0) {
            return 0;
        }
        int numberOfParts = matrix[0].length / nthreads;
        int resultId = 0;
        int fromColumn = 0;
        int toColumn = fromColumn + numberOfParts - 1;

        for (int i = 0; i < nthreads; i++) {
            ColumnSummator columnSummator = new ColumnSummator(
                    fromColumn,
                    toColumn,
                    matrix,
                    resultId,
                    result,
                    syncObject);

            threadList.add(new Thread(columnSummator));

            fromColumn = toColumn + 1;
            if (i == nthreads - 2) {
                toColumn = matrix[0].length - 1;
            } else {
                toColumn = fromColumn + numberOfParts - 1;
            }
            resultId++;
        }

        for (Thread thread : threadList) {
            thread.start();
        }

        synchronized (syncObject) {
            if (syncObject.get() != 0) {
                syncObject.wait();
            }
        }

        int sum = 0;
        for (int threadResult : result) {
            sum += threadResult;
        }

        return sum;
    }
}