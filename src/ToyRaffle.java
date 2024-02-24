import java.util.PriorityQueue;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ToyRaffle {
    // Очередь с приоритетом для хранения игрушек. Игрушки упорядочены по весу, при этом игрушки с меньшим весом имеют более высокий приоритет.
    private PriorityQueue<Toy> toys;
    // Генератор случайных чисел для выбора игрушек.
    private Random random;
    public ToyRaffle(String[] toyData) {
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
                "1,Мягкие игрушки персонажей Геншин Импакт,10",
                "2,Мягкие игрушки персонажей Хонкай Стар Рейл,9",
                "3,Куклы Барби,8",         
                "4,Фигурки аниме,6",
                "5,Мишки Тедди,5"
                "6,Браслеты аниме,4",
                "7,Паззлы и головоломки,2",
        };
        // Создание объекта класса ToyRaffle.
        ToyRaffle raffle = new ToyRaffle(toyData);

        // Создание файла для записи результатов розыгрыша.
        try {
            File file = new File("toy_raffle_results.txt");
            FileWriter writer = new FileWriter(file);

            // Проведение розыгрыша 10 игрушек и запись результатов в файл.
            for (int i = 0; i < 10; i++) {
                Toy toy = raffle.getToy();
                writer.write(toy.getName() + "\n");
            }

            // Закрытие файла.
            writer.close();
        } catch (IOException e) {
            // Обработка исключения ввода-вывода.
            e.printStackTrace();
        }
    }
    private static class Toy implements Comparable<Toy> {
        // Идентификатор игрушки.
        private int id;
        // Название игрушки.
        private String name;
        // Вес игрушки.
        private int weight;

        public Toy(int id, String name, int weight) {
            this.id = id;
            this.name = name;
            this.weight = weight;
        }
        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public int getWeight() {
            return weight;
        }
        @Override
        public int compareTo(Toy other) {
            // Сравнение игрушек по весу.
            return Integer.compare(this.weight, other.weight);
        }
    }
}
