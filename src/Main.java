import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Main {

    public static final int waitersNumber = 5;
    public static final int consumersNumber = 3;

    public static Waiter[] waiters;
    public static Consumer[] consumers;
    public static ArrayList<Order> orderList;

    public static void main(String[] args) throws InterruptedException {

        init(waitersNumber, consumersNumber);

        for (Waiter waiter : waiters) {
            waiter.start();
        }
        for (Consumer consumer : consumers) {
            consumer.start();
        }

        Window app = new Window(waiters, consumers, orderList);

        while (true) {
            sleep(3000);
            update();
        }

    }

    public static void update() {

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

        waiters = new Waiter[waitersNumber];
        for (int i = 0; i < waitersNumber; i++) {
            waiters[i] = new Waiter(nameFactory());
        }

        consumers = new Consumer[customersNumber];
        for (int i = 0; i < customersNumber; i++) {
            consumers[i] = new Consumer(nameFactory());
        }
    }
}
