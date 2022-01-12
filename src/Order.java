public class Order {

    private static int idFactory;
    private final int id;

    static {
        idFactory = 0;
    }

    public Order() {
        id = idFactory++;
    }


    public String toString() {
        return String.format(" %s = ", id);
    }

}