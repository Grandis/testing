/**
 * Created with IntelliJ IDEA.
 * User: grandis
 * Date: 06.11.12
 * Time: 20:40
 * To change this template use File | Settings | File Templates.
 */

public class Testing {
    public static void main (String args[]) {
        Package pckj[] = Package.getPackages();

        for (int i=0; i<pckj.length; i++) {
            System.out.println(
                    pckj[i].getName() + "  " +
                    pckj[i].getImplementationTitle() + "  " +
                    pckj[i].getImplementationVendor() + "  " +
                    pckj[i].getImplementationVersion()
            );
        }
    }
}
