import java.util.Random;

public class Utils {
    public static int getTimeSleep() {
        final Random random = new Random();
        int tm = random.nextInt(10000);
        if (tm < 100)
            tm *= 100;
        else if (tm < 1000)
            tm *= 10;
        return tm;
    }
}
