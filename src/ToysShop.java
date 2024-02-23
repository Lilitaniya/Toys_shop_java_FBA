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
    public Toy getToy() {
        // Подсчёт общего веса всех игрушек.
        int totalWeight = 0;
        for (Toy toy : toys) {
            totalWeight += toy.getWeight();
        }

        // Выбор случайного веса в пределах общего веса всех игрушек.
        int randomWeight = random.nextInt(totalWeight);

        // Выбор игрушки с весом, который больше или равен случайному весу.
        int currentWeight = 0;
        Toy selectedToy = null;

        for (Toy toy : toys) {
            currentWeight += toy.getWeight();
            if (randomWeight < currentWeight) {
                selectedToy = toy;
                break;
            }
        }
        // Возвращение выбранной игрушки.
        return selectedToy;
    }

    public static void main(String[] args) {
        // Данные об игрушках в формате "id,название,вес".
        String[] toyData = {
                "1,Мишка Тедди,10",
                "2,Куколка,8",
                "3,Фигурка героя,6",
                "4,Фигурка из Геншина,4",
                "5,Паззлы,2"
        };
