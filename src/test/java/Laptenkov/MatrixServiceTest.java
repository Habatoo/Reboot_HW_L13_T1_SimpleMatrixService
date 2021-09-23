package Laptenkov;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс {@link MatrixServiceTest}
 * тестирует публичный метод {@link MatrixService#sum(int[][], int)}
 * класса {@link MatrixService}.
 */
class MatrixServiceTest {

    MatrixService matrixService;
    int[][] emptyMatrix;
    int[][] notEmptyMatrix_33;
    int[][] notEmptyMatrix_99;

    /**
     * Инициализация объекта {@link MatrixService}
     * и тестовых матриц для расчета суммы.
     */
    @BeforeEach
    void setUp() {
        matrixService = new MatrixService();
        emptyMatrix = new int[0][0];
        notEmptyMatrix_99 = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                notEmptyMatrix_99[j][i] = 1;
            }
        }
        notEmptyMatrix_33 = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                notEmptyMatrix_33[j][i] = 1;
            }
        }
    }

    /**
     * Очистка данных объекта {@link MatrixService}
     * и тестовых матриц после расчета суммы.
     */
    @AfterEach
    void tearDown() {
        matrixService = null;
        emptyMatrix = null;
        notEmptyMatrix_99 = null;
        notEmptyMatrix_33 = null;
    }

    /**
     * Метод {@link MatrixServiceTest#sumEmptySuccess_Test}
     * тестирует метод {@link MatrixService#sum(int[][], int)}.
     * Сценарий отрабатывает передачу пустой матрицы и подсчет суммы элемнтов в
     * одном потоке и сравнении результата суммирования с тестовым значением
     */
    @Test
    void sumEmptySuccess_Test() throws InterruptedException {
        Assert.assertEquals(0, matrixService.sum(emptyMatrix, 1));
    }

    /**
     * Метод {@link MatrixServiceTest#sumEmptyFail_Test}
     * тестирует метод {@link MatrixService#sum(int[][], int)}.
     * Сценарий отрабатывает передачу пустой матрицы и подсчет суммы элемнтов в
     * двух потоках и сравнении результата суммирования с тестовым значением
     */
    @Test
    void sumEmptyFail_Test() throws InterruptedException {
        Assert.assertEquals(0, matrixService.sum(emptyMatrix, 2));
    }

    /**
     * Метод {@link MatrixServiceTest#sumNotEmpty99Success1_Test}
     * тестирует метод {@link MatrixService#sum(int[][], int)}.
     * Сценарий отрабатывает передачу не пустой марицы и подсчет суммы элемнтов в
     * одном потоке и сравнении результата суммирования с тестовым значением
     */
    @Test
    void sumNotEmpty99Success1_Test() throws InterruptedException {
        Assert.assertEquals(81, matrixService.sum(notEmptyMatrix_99, 1));
    }

    /**
     * Метод {@link MatrixServiceTest#sumNotEmpty99Success3_Test}
     * тестирует метод {@link MatrixService#sum(int[][], int)}.
     * Сценарий отрабатывает передачу не пустой матрицы и подсчет суммы элемнтов в
     * двух потоках и сравнении результата суммирования с тестовым значением
     */
    @Test
    void sumNotEmpty99Success3_Test() throws InterruptedException {
        Assert.assertEquals(81, matrixService.sum(notEmptyMatrix_99, 3));
    }

    /**
     * Метод {@link MatrixServiceTest#sumNotEmpty33Success1_Test}
     * тестирует метод {@link MatrixService#sum(int[][], int)}.
     * Сценарий отрабатывает передачу не пустой марицы и подсчет суммы элемнтов в
     * одном потоке и сравнении результата суммирования с тестовым значением
     */
    @Test
    void sumNotEmpty33Success1_Test() throws InterruptedException {
        Assert.assertEquals(9, matrixService.sum(notEmptyMatrix_33, 1));
    }

    /**
     * Метод {@link MatrixServiceTest#sumNotEmpty33Success2_Test}
     * тестирует метод {@link MatrixService#sum(int[][], int)}.
     * Сценарий отрабатывает передачу не пустой матрицы и подсчет суммы элемнтов в
     * двух потоках и сравнении результата суммирования с тестовым значением
     */
    @Test
    void sumNotEmpty33Success2_Test() throws InterruptedException {
        Assert.assertEquals(9, matrixService.sum(notEmptyMatrix_33, 2));
    }
}