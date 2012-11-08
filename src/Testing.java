/**
 * Created with IntelliJ IDEA.
 * User: grandis
 * Date: 06.11.12
 * Time: 20:40
 * To change this template use File | Settings | File Templates.
 */

class NewThread extends Thread {
    boolean suspendFlag;

    NewThread (String threadName, ThreadGroup tgOb) {
        super (tgOb, threadName);
        System.out.println("Новый поток: " + this);
        suspendFlag = false;
        start();
    }

    public void run () {
        try {
            for (int i=5; i>0; i--) {
                System.out.println(getName() + ": " + i);
                Thread.sleep(1_000);
                synchronized (this) {
                    while (suspendFlag)
                        wait();
                }
            }
        }
        catch (InterruptedException ex) {
            System.out.println("Исключение в " + getName() +": " + ex);
        }
        System.out.println(getName() + " завершается.");
    }

    synchronized void mySuspend () {
        suspendFlag = true;
    }

    synchronized void myResume () {
        suspendFlag = false;
        notify();
    }
}

public class Testing {
    public static void main (String args[]) {
        ThreadGroup groupA = new ThreadGroup("Группа А");
        ThreadGroup groupB = new ThreadGroup("Группа Б");

        NewThread ob1 = new NewThread("Один", groupA);
        NewThread ob2 = new NewThread("Два", groupA);
        NewThread ob3 = new NewThread("Три", groupB);
        NewThread ob4 = new NewThread("Четыре", groupB);

        System.out.println("Вывод из list(): ");
        groupA.list();
        groupB.list();
        System.out.println();

        System.out.println("Прерывается группа А");
        Thread tga[] = new Thread[groupA.activeCount()];
        groupA.enumerate(tga); //получить потоки группы

        for (int i=0; i<tga.length; i++) {
            ((NewThread)tga[i]).mySuspend(); //приостановить каждый поток
        }

        try {
            Thread.sleep(4_000);
        }
        catch (InterruptedException ex) {
            System.out.println("Главный поток прерван." + ex);
        }

        System.out.println("Возобновление группы А");

        for (int i=0; i<tga.length; i++) {
            ((NewThread)tga[i]).myResume(); //возобновить все потоки в группе
        }

        //ожидать завершения потоков
        try {
            System.out.println("Ожидание остановки потоков.");
            ob1.join();
            ob2.join();
            ob3.join();
            ob4.join();
        }
        catch (Exception ex) {
            System.out.println("Исключение в основном потоке.");
        }
        System.out.println("Основной поток заверншен.");
    }
}
