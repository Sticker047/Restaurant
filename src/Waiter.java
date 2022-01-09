import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Waiter extends Thread {

    private static int idCount;
    private final int id;
    private final String name;
    private Consumer currentConsumer;

    private enum Status {
        WAITING,
        GETTING_ORDER,
        WAITING_FOOD,
        FULFILL_ORDER,
        GOING
    }

    public Status status = Status.WAITING;

    static {
        idCount = 0;
    }

    public Waiter(String name) {
        this.name = name;
        id = idCount++;
    }

    public void run() {
        try {
            while (!isInterrupted()) {
                switch (status) {
                    case WAITING -> waiting(Main.consumers);
                    case GOING -> going();
                    case GETTING_ORDER -> gettingOrder();
                    case WAITING_FOOD -> waitingFood();
                    case FULFILL_ORDER -> fulfillOrder();
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void going() throws InterruptedException {
        status = Status.GOING;
        sleep(getTimeSleep());

        gettingOrder();
    }

    public Order gettingOrder() throws InterruptedException {
        status = Status.GETTING_ORDER;
        Order order = writingOrder();
        fulfillOrder();

        return order;
    }

    public void fulfillOrder() throws InterruptedException {
        status = Status.FULFILL_ORDER;
        sleep(getTimeSleep());
        status = Status.WAITING_FOOD;
    }

    public Order writingOrder() throws InterruptedException {
        Order order = new Order(this, currentConsumer);
        sleep(getTimeSleep());
        return order;
    }

    public void waiting(Consumer @NotNull [] consumerList) throws InterruptedException {
        sleep(getTimeSleep());
        for (Consumer value : consumerList) {
            {
                if (value.getStatus() != Consumer.Status.WAITING) {
                    continue;
                }
                currentConsumer = value;
                this.status = Status.GETTING_ORDER;
                currentConsumer.createOrder();
                break;
            }
        }
    }

    public void waitingFood() throws InterruptedException {
        sleep(getTimeSleep());
    }

    public static int getTimeSleep() {
        final Random random = new Random();
        int tm = random.nextInt(1000);
        if (tm < 10)
            tm *= 100;
        else if (tm < 100)
            tm *= 10;
        return tm;
    }

    public String toString() {
        return name + " id = " + id + " " + status;
    }
}
