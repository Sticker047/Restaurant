public class Consumer extends Thread implements Drawable {

    private static int counting;

    private String name;
    private int account;
    private Status status;
    private Waiter waiter;
    private Order order;

    @Override
    public void draw(int x, int y) {

    }

    public enum Status {
        LOOKING_FOR_TABLE,
        WAITING,
        CREATE_ORDER,
        EATING,
        PAYING,
    }

    static {
        counting = 0;
    }

    public Consumer(String name) {

        account = counting++;
        this.name = name;
        status = Status.WAITING;

    }

    public void run() {
        try {
            while (!isInterrupted()) {
                switch (status) {
                    case LOOKING_FOR_TABLE -> {
                        lookingForTable();
                    }
                    case WAITING -> {
                        waiting();
                    }
                    case CREATE_ORDER -> {
                        createOrder();
                    }
                    case EATING -> {
                        eating();
                    }
                    case PAYING -> {
                        paying();
                    }
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void lookingForTable() throws InterruptedException {
        sleep(Waiter.getTimeSleep());
    }

    public void waiting() throws InterruptedException {
        sleep(Waiter.getTimeSleep());
    }

    public void createOrder() {
        order = new Order(waiter, this);
        Main.orderList.add(order);
    }

    public void eating() throws InterruptedException {
        sleep(Waiter.getTimeSleep());
    }

    public void paying() throws InterruptedException {
        sleep(Waiter.getTimeSleep());
    }

    public Status getStatus() {
        return status;
    }

    public String toString() {
        return name + " account = " + account + " " + status;
    }

}
