import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Worker implements Runnable
{
    private int id;
    public Worker(int id)
    {
        this.id = id;
    }
    @Override
    public void run()
    {
        System.out.println("Task with id - " + id + " is in work -" + Thread.currentThread().getName());
        long duration = (long)Math.random() * 5;
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ThreadPool
{
    public static void main(String[] args) {

        //3 threads in thread pool
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for(int i =0 ; i < 10 ; ++i)
        {
            executor.execute(new Worker(i+1));
        }

        //prevent the executor to execute any further tasks
        executor.shutdown();

        //terminate actual running tasks
        try {
            if(!executor.awaitTermination(1000 , TimeUnit.MILLISECONDS))
            {
                executor.shutdownNow();
            }
        }catch (InterruptedException e)
        {
            executor.shutdownNow();
        }
    }
}
