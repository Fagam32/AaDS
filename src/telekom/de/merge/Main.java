package telekom.de.merge;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * С помощью сортировки слиянием необходимо осуществить сортировку (в порядке неубывания) заданного массива целых чисел. После каждого осуществленного слияния необходимо выводить индексы граничных элементов и их значения.
 * <p>
 * Входной файл input.txt
 * В первой строке входного файла содержится число N (1 ≤ N ≤ 10^5) — число элементов в массиве. Во второй строке находятся N целых чисел, по модулю не превосходящих 10^9.
 * <p>
 * Выходной файл output.txt
 * Выходной файл состоит из нескольких строк.
 * <p>
 * В последней строке – отсортированный в порядке неубывания массив, данный на входе. Между любыми двумя числами должен стоять ровно один пробел.
 * <p>
 * Все предшествующие строки описывают осуществленные слияния, по одному на каждой строке. Каждая такая строка должна содержать по четыре числа: begin end A B, где begin — индекс начала области слияния, end — индекс конца области слияния, A — значение первого элемента области слияния, B — значение последнего элемента области слияния.
 * <p>
 * Все индексы начинаются с единицы (то есть, 1 ≤ begin ≤ end ≤ N). Информацию о слиянии нужно выводить даже для подмассива длиной 1.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        var scanner = new Scanner(new File("input.txt"));
        var writer = new FileWriter(new File("output.txt"));

        int n = scanner.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        sort(array, 0, n - 1, writer);

        for (int i : array) {
            writer.write(i + " ");
        }
        writer.close();
    }

    private static void sort(int[] a, int l, int r, FileWriter writer) throws IOException {
        if (l >= r)
            return;

        int m = (r + l) / 2;

        sort(a, l, m, writer);
        sort(a, m + 1, r, writer);

        merge(a, l, m, r, writer);
    }

    private static void merge(int[] a, int l, int m, int r, FileWriter writer) throws IOException {
        int i, j, k;
        int lSize = m - l + 1, rSize = r - m;
        int[] left = new int[lSize];
        int[] right = new int[rSize];

        for (i = 0; i < lSize; ++i)
            left[i] = a[l + i];
        for (j = 0; j < rSize; ++j)
            right[j] = a[m + 1 + j];

        i = 0;
        j = 0;
        k = l;

        while (i < lSize && j < rSize) {
            if (left[i] <= right[j])
                a[k++] = left[i++];
            else
                a[k++] = right[j++];
        }

        while (i < lSize)
            a[k++] = left[i++];
        while (j < rSize)
            a[k++] = right[j++];

        writer.write((l + 1) + " " + (r + 1) + " " + a[l] + " " + a[r] + "\n");
    }
}
