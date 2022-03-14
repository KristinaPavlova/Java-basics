import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class App implements Callable<String>
{
    private int id;
    public App(int id)
    {
        this.id = id;
    }
    @Override
    public String call() throws Exception {
        Thread.sleep(200);
        return ("id:" + id);
    }
}

public class CallableInterface
{
    public static void main(String[] args)
    {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Future<String>> list = new ArrayList<>();
        for(int i = 0 ; i < 10 ; ++i)
        {
            Future<String> future = executor.submit(new App(i+1));
            list.add(future);
        }

        for(Future<String> f : list)
        {
            try
            {
                System.out.println(f.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        executor.shutdown();

        try
        {
            if(!executor.awaitTermination(1000 , TimeUnit.MILLISECONDS))
            {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            e.printStackTrace();
        }
    }

}
