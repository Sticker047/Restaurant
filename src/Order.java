import org.jetbrains.annotations.NotNull;

import static java.lang.Thread.sleep;

public class Order {

    private static int idFactory;
    private int id;

    private String text;

    private boolean actual;

    private Waiter waiter;
    private Consumer consumer;

    private enum Status {

        ORDERING,
        IN_PROCESS,
        WAITING_WAITER_2,
        READY,
        CANCELLED
    }

    private Status status;

    static {
        idFactory = 0;
    }

    public Order(@NotNull Waiter waiter, Consumer consumer) {
        status = Status.ORDERING;
        try {
            waiter.writingOrder();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        id = idFactory++;
        actual = true;

        status = Status.IN_PROCESS;
    }

    public void cooking() {
        status = Status.WAITING_WAITER_2;
    }

    public void eating() {
        status = Status.READY;
    }

    public void cancel() {
        actual = false;
        status = Status.CANCELLED;
    }

    public String toString() {
        return " id = " + id + " " + status;
    }

}