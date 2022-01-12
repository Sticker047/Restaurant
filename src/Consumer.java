public class Consumer extends Thread {

    private static int counting;

    private final String name;
    private final int account;
    private Status status;

    public enum Status {
        LOOKING_FOR_TABLE,
        WAITING,
        EATING,
        PAYING,
        FINISHED
    }

    static {
        counting = 0;
    }

    public Consumer(String name) {

        account = counting++;
        this.name = name;
        status = Status.LOOKING_FOR_TABLE;

    }

    public void run() {
        try {
            while (!isInterrupted()) {
                switch (status) {
                    case LOOKING_FOR_TABLE -> lookingForTable();
                    case WAITING -> waiting();
                    case EATING -> eating();
                    case PAYING -> paying();
                    case FINISHED -> {}
                    default -> throw new IllegalStateException("Unexpected value: " + status);
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void lookingForTable() throws InterruptedException {
        sleep(Waiter.getTimeSleep());
        status = Status.WAITING;
    }

    public void waiting() throws InterruptedException {
        sleep(Waiter.getTimeSleep());
    }

    public void createOrder() {
        status = Status.EATING;
        Order order = new Order();
        Main.orderList.add(order);

    }

    public void eating() throws InterruptedException {
        sleep(Waiter.getTimeSleep());
        status = Status.PAYING;
    }

    public void paying() throws InterruptedException {
        sleep(Waiter.getTimeSleep());
        status = Status.FINISHED;
    }

    public Status getStatus() {
        return status;
    }

    public String toString() {
        return String.format("%-12s", name) + " account = " + account + "  " + status;
    }

}
