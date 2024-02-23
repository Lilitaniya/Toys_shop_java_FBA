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
