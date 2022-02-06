import java.util.ArrayList;

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
        sleep(Utils.getTimeSleep());
    }

    public void gettingOrder() throws InterruptedException {
        sleep(Utils.getTimeSleep());
        status = Status.FULFILL_ORDER;
    }

    public void fulfillOrder() throws InterruptedException {
        sleep(Utils.getTimeSleep());
        status = Status.WAITING;
    }

    public synchronized void waiting(ArrayList<Consumer> consumerList) throws InterruptedException {
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

    public String toString() {

        return String.format("%-15s%n id = %s %s", name, id, status);
    }
}
