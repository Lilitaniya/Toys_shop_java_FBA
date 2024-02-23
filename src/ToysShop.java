import java.util.PriorityQueue;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ToysShop {

    // Очередь с приоритетом для хранения игрушек. Игрушки упорядочены по весу, при этом игрушки с меньшим весом имеют более высокий приоритет.
    private PriorityQueue<Toy> toys;

    // Генератор случайных чисел для выбора игрушек.
    private Random random;
    public ToysShop(String[] toyData) {
        // Инициализация очереди с приоритетом и генератора случайных чисел.
        toys = new PriorityQueue<>();
        random = new Random();

        // Добавление игрушек в очередь с приоритетом.
        for (String toy : toyData) {
            String[] fields = toy.split(",");
            int id = Integer.parseInt(fields[0]);
            String name = fields[1];
            int weight = Integer.parseInt(fields[2]);

            toys.add(new Toy(id, name, weight));
        }
    }
