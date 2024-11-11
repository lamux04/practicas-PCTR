import java.util.function.Function;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class integCallable {
    static Function<Double, Double> f1 = (x) -> Math.sin(x);
    static Function<Double, Double> f2 = (x) -> x;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca el numero de puntos: ");
        int puntos = scanner.nextInt();

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Double> Hilo1 = executor.submit(new Integral(f1, puntos, 1));
        Future<Double> Hilo2 = executor.submit(new Integral(f2, puntos, 2));
        
        System.out.println("Funcion 1 -> " + Hilo1.get());
        System.out.println("Funcion 2 -> " + Hilo2.get());

        executor.shutdown();

        scanner.close();
    }
}

class Integral implements Callable<Double>
{
    Function<Double, Double> f;
    int puntos;
    int id;
    public Integral (Function<Double, Double> f, int puntos, int id)
    {
        this.f = f;
        this.puntos = puntos;
        this.id = id;
    }

    public Double call()
    {
        int puntos_validos = 0;
        for (int i = 0; i < puntos; ++i)
        {
            double y = ThreadLocalRandom.current().nextDouble(1);
            double f_x = f.apply(ThreadLocalRandom.current().nextDouble(1));
            if (f_x > y)
            {
                
                puntos_validos++;
            }
        }
        return ((double) puntos_validos/puntos);
    }
}
