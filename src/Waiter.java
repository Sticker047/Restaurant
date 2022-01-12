import java.util.ArrayList;
import java.util.Random;

public class Waiter extends Thread {

    private static int idCount;
    private final int id;
    private final String name;

    private enum Status {
        WAITING,
        GETTING_ORDER,
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
                    case WAITING -> waiting(Main.consumerArrayList);
                    case GOING -> going();
                    case GETTING_ORDER -> gettingOrder();
                    case FULFILL_ORDER -> fulfillOrder();
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public synchronized void going() throws InterruptedException {
        status = Status.GOING;
        sleep(getTimeSleep());
    }

    public void gettingOrder() throws InterruptedException {
        sleep(getTimeSleep());
        status = Status.FULFILL_ORDER;
    }

    public void fulfillOrder() throws InterruptedException {
        sleep(getTimeSleep());
        status = Status.WAITING;
    }

    public void waiting(ArrayList<Consumer> consumerList) throws InterruptedException {
        sleep(1000);
        for (Consumer value : consumerList) {
            {
                if (value.getStatus() != Consumer.Status.WAITING) {
                    continue;
                }
                this.status = Status.GETTING_ORDER;
                value.createOrder();
                break;
            }
        }
    }

    public static int getTimeSleep() {
        final Random random = new Random();
        int tm = random.nextInt(10000);
        if (tm < 100)
            tm *= 100;
        else if (tm < 1000)
            tm *= 10;
        return tm;
    }

    public String toString() {

        return String.format("%-15s%n id = %s %s", name, id, status);
    }
}
