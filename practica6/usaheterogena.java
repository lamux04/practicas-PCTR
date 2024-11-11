import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class usaheterogena implements Runnable
{
    heterogenea h;

    public usaheterogena(heterogenea h)
    {
        this.h = h;
    }

    public void run()
    {
        for (int i = 0; i < 1000; ++i)
        {
            h.incrementar_n();
            h.incrementar_m();
        }
    }
    public static void main(String[] args) 
    {
        heterogenea he = new heterogenea();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(new usaheterogena(he));
        executor.submit(new usaheterogena(he));

        try{
            executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
        }catch (Exception e)
        {

        }

        executor.shutdown();

        System.out.println("n -> " + he.ver_n());
        System.out.println("m -> " + he.ver_m());
    }
}
