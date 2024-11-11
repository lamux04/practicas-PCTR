import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class usaTADListaSegura implements Runnable
{
    tADListaSegura l;
    int id;

    public usaTADListaSegura(tADListaSegura l, int id)
    {
        this.l = l;
        this.id = id;
    }

    public void run()
    {
        l.insertar(id, 0);
        l.imprimir();
    }

    public static void main(String[] args) 
    {
        tADListaSegura lista = new tADListaSegura(50);

        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; ++i)
        {
            executor.submit(new usaTADListaSegura(lista, i));
        }

        executor.shutdown();

    }

    
}
