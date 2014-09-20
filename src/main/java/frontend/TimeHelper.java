package frontend;

/**
 * Created by vadim on 21.09.14.
 */
public class TimeHelper {
    public static void sleep(int period) {
        try {
            Thread.sleep(period);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}