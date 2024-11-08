import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class parallelBigFactorial 
{
    public static void main(String[] args) 
    {
        int a = 342532;
        final int nhilos = 4;
        BigInteger resultado = new BigInteger("1");
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Callable<BigInteger>> listaTareas = new ArrayList<>();

        double inicio = System.nanoTime();
        
        for (int i = 0; i < nhilos; ++i)
        {
            listaTareas.add(new parcialBigFactorial(a, i, nhilos));
        }

        try
        {
            List<Future<BigInteger>> listaFutures = executor.invokeAll(listaTareas);

            for (Future<BigInteger> future : listaFutures)
            {
                resultado = resultado.multiply(future.get());
            }
                
        } catch (Exception e)
        {}
        
        executor.shutdown();
        
        System.out.println(resultado);
        
        double fin = System.nanoTime();
        
        System.out.println((fin - inicio) / Math.pow(10, 9));
    }
}

class parcialBigFactorial implements Callable<BigInteger>
{
    BigInteger resultadoParcial = new BigInteger("1");
    int a;
    int id, nhilos;

    public parcialBigFactorial(int a, int id, int nhilos)
    {
        this.a = a;
        this.id = id;
        this.nhilos = nhilos;
    }

    public BigInteger call() throws Exception
    {
        for (int i = id + 1; i <= a; i += nhilos)
        {
            resultadoParcial = resultadoParcial.multiply(BigInteger.valueOf(i));
        }

        return resultadoParcial;
    }
}