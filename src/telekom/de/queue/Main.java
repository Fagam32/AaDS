package telekom.de.queue;
/**
 * Реализуйте работу очереди. На вход программе подаются строки, содержащие команды. Каждая строка содержит одну команду:
 * команда «+ X» означает добавление в очередь числа X, по модулю не превышающего 10^9;
 * команда «−» означает изъятие элемента из очереди;
 * команда «?» означает поиск минимального элемента в очереди.
 * <p>
 * Входной файл input.txt
 * В первой строке содержится N (1 ≤ N ≤ 10^6) — число команд. В последующих строках содержатся команды, по одной в каждой строке.
 * <p>
 * Выходной файл output.txt
 * Для каждой операции поиска минимума в очереди выведите её результат. Результаты должны быть выведены в том порядке, в котором эти операции встречаются во входном файле. Входные данные гарантируют, что операции извлечения или поиска минимума для пустой очереди не производятся.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static Deque<Integer> deque = new ArrayDeque<>();
    public static Queue<Integer> queue = new ArrayDeque<>();
    public static ArrayList<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        var scanner = new Scanner(new File("input.txt"));
        while (scanner.hasNext()) {
            processSymbol(scanner, scanner.next());
        }

        writeAnswer(answer);
    }

    private static void writeAnswer(ArrayList<Integer> answer) throws IOException {
        var writer = new FileWriter("output.txt");

        for (int i : answer) {
            writer.write(i + "\n");
        }

        writer.close();
    }

    private static void processSymbol(Scanner scanner, String symbol) throws IOException {
        switch (symbol) {
            case "+":
                add(scanner.nextInt());
                break;
            case "-":
                remove();
                break;
            case "?":
                minimum();
                break;
        }
    }

    private static void add(int x) {
        if (queue.isEmpty()) {
            queue.add(x);
            deque.addLast(x);
        } else {
            queue.add(x);
            while (!deque.isEmpty() && deque.peekLast() > x) {
                deque.pollLast();
            }
            deque.addLast(x);
        }
    }

    private static void remove() {
        if (queue.peek().equals(deque.peekFirst())) {
            queue.poll();
            deque.pollFirst();
        } else {
            queue.poll();
        }
    }

    private static void minimum() {
        answer.add(deque.peek());
    }
}
