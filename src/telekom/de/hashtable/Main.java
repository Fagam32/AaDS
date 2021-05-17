package telekom.de.hashtable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    /**
     * Реализовать контейнер множество с помощью хэш-таблицы,
     * поддерживающий операции «добавление ключа», «удаление ключа», «проверка существования ключа».
     * <p>
     * На вход программе подаются строки, содержащие команды. Каждая строка содержит одну команду.
     * <p>
     * Команда «+ X» означает добавление в множество числа X, по модулю не превышающего 10^9.
     * <p>
     * Команда «− X» означает изъятие элемента из множества.
     * <p>
     * Команда «? X» означает проверку наличия ключа X в множестве.
     */
    public static void main(String[] args) throws IOException {
        var scanner = new Scanner(new File("input.txt"));
        var writer = new FileWriter("output.txt");

        var set = new HashSet<Integer>();

        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            var command = scanner.next();
            switch (command) {
                case "+":
                    set.add(scanner.nextInt());
                    break;
                case "-":
                    set.remove(scanner.nextInt());
                    break;
                case "?":
                    boolean res = set.contains(scanner.nextInt());
                    writer.write(Boolean.valueOf(res).toString() + "\n");
                    break;
            }
        }
        writer.close();
    }
}
