import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Synchronize_Lock {

    public static int counter = 0;

    public static Lock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    public static void producer() throws InterruptedException {
        lock.lock();
        System.out.println("producer method...");

        condition.await();

        System.out.println("producer method again...");

        lock.unlock();
    }

    public static void consumer() throws InterruptedException {

        Thread.sleep(200);

        lock.lock();
        System.out.println("consumer method..");

        Thread.sleep(200);
        condition.signal();
        lock.unlock();
    }

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        try
        {
            t1.join();
            t2.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
