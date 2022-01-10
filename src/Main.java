import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static final int waitersNumber = 5;
    public static final int consumersNumber = 3;

    public static Waiter[] waiters;
    public static Consumer[] consumers;
    public static ArrayList<Order> orderList = new ArrayList<>();

    public static void main(String[] args){

        init(waitersNumber, consumersNumber);

        for (Waiter waiter : waiters) {
            waiter.start();
        }
        for (Consumer consumer : consumers) {
            consumer.start();
        }

        Window app = new Window(waiters, consumers);
        app.setVisible(true);

        while (app.isVisible()) {
            app.update(waiters, consumers);
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
