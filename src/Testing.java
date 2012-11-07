/**
 * Created with IntelliJ IDEA.
 * User: grandis
 * Date: 06.11.12
 * Time: 20:40
 * To change this template use File | Settings | File Templates.
 */
public class Testing {
    public static void main (String args[]) {
        double begin, end;
        Integer arr[] = new Integer[10_000_000];

        begin = System.currentTimeMillis();
        for (int i=0; i<arr.length; i++) arr[i]=i;
        end = System.currentTimeMillis();

        System.out.println("Время выполнения: " + (end-begin)/1000 + " секунд.");
    }
}
