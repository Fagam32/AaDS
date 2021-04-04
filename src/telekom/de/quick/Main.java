package telekom.de.quick;

/**
 * Алгоритм QuickSort является быстрой сортировкой в среднем, но существуют тесты, на которых она работает очень долго.
 * <p>
 * Требуется написать программу, которая генерирует тест, на котором быстрая сортировка сделает наибольшее число сравнений элементов массива, если в качестве опорного элемента выбирается тот, что находится по середине массива.
 * <p>
 * Входной файл input.txt
 * В первой строке находится число N (1 ≤ N ≤ 100 000).
 * <p>
 * Выходной файл output.txt
 * Любая перестановка чисел от 1 до N, на которой быстрая сортировка выполнит максимальное число сравнений.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        var scanner = new Scanner(new File("input.txt"));
        int n = scanner.nextInt();

        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = i + 1;
        }
        for (int i = 0; i < n - 1; i++)
            swap(array, i, i / 2);

        writeAnswer(array);
    }

    private static void writeAnswer(int[] answer) throws IOException {
        var writer = new FileWriter("output.txt");
        for (int i : answer) {
            writer.write(i + "\n");
        }
        writer.close();
    }

    private static void swap(int[] array, int a, int b) {
        int x = array[a];
        array[a] = array[b];
        array[b] = x;
    }
}
