import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static final int waitersNumber = 2;
    public static final int consumersNumber = 10;

    public static ArrayList<Waiter> waiterArrayList;
    public static ArrayList<Consumer> consumerArrayList;
    public static ArrayList<Order> orderList = new ArrayList<>();

    public static void main(String[] args) {

        init(waitersNumber, consumersNumber);

        for (Waiter waiter : waiterArrayList) {
            waiter.start();
        }
        for (Consumer consumer : consumerArrayList) {
            consumer.start();
        }

        Window app = new Window(waiterArrayList, consumerArrayList);
        app.setVisible(true);

        while (app.isVisible()) {
            app.update(waiterArrayList, consumerArrayList);
            addConsumer();
        }

    }

    public static String nameFactory() {
        List<String> names = new ArrayList<>();

        names.add("Василий");
        names.add("Пётр");
        names.add("Аркадий");
        names.add("Дмитрий");
        names.add("Мефодий");
        names.add("Карл");

        Random random = new Random();

        return names.get((random.nextInt(names.size())));
    }

    public static void init(int waitersNumber, int customersNumber) {

        waiterArrayList = new ArrayList<>();
        for (int i = 0; i < waitersNumber; i++) {
            waiterArrayList.add(new Waiter(nameFactory()));
        }

        consumerArrayList = new ArrayList<>();
        for (int i = 0; i < customersNumber; i++) {
            consumerArrayList.add(new Consumer(nameFactory()));
        }
    }

    public static void addConsumer() {

//        Random random = new Random(6);
//        if (random.nextInt() <= 5) return;
//
        consumerArrayList.add(new Consumer(nameFactory()));
    }
}
