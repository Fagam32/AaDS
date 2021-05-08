package telekom.de.radix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static int[][] radixsort(int[][] a, int n, int f)
    {
        int i;
        int[][] b = new int[n + 1][n + 1];
        int[] c = new int[27];

        for (i = 1; i < n + 1; ++i)
            c[a[i][f]]++;

        for (i = 1; i < 27; ++i)
            c[i] += c[i - 1];

        for (i = n; i > 0; --i) {
            b[c[a[i][f]]] = a[i];
            c[a[i][f]]--;
        }

        return b;
    }


    public static void main(String[] args) throws IOException {
        var scanner = new Scanner(new File("input.txt"));
        var writer = new FileWriter("output.txt");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();

        int[][] array = new int[n + 1][m + 1];

        for (int i = 1; i < n + 1; ++i) {
            array[i][0] = i;
        }
        scanner.nextLine();
        for (int i = 1; i < m + 1; ++i) {
            String line = scanner.nextLine();
            for (int j = 1; j < n + 1; ++j) {
                array[j][i] = line.charAt(j - 1) - 96;
            }
        }

        for (int i = 1; i <= k; ++i)
            array = radixsort(array, n, m - i + 1);

        for (int i = 1; i < n + 1; ++i)
            writer.write(array[i][0] + " ");
        writer.close();
    }
}
