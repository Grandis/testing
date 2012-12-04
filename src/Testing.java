import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: grandis
 * Date: 06.11.12
 * Time: 20:40
 * To change this template use File | Settings | File Templates.
 */

public class Testing {
    public static void main (String args[]) {
        Calendar calendar = Calendar.getInstance();
        long startTimer, endTimer;
        float spentTime;

        startTimer = System.currentTimeMillis();
        try {
            System.out.println("Start.");
            Thread.sleep(3000);
            System.out.println("The end.");
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        endTimer = System.currentTimeMillis();
        spentTime = (endTimer - startTimer) / 1_000;
        try (Formatter formatter = new Formatter()) {
            formatter.format("Время выполнения программы составило %.2f сек.", spentTime);
            formatter.format("\n%tB %<td %<tA %<tT", calendar);
            System.out.println(formatter);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
