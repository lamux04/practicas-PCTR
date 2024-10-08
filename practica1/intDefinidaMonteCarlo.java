import java.util.function.Function;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class intDefinidaMonteCarlo {
    static Function<Double, Double> f1 = (x) -> Math.sin(x);
    static Function<Double, Double> f2 = (x) -> x;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca el numero de puntos: ");
        int puntos = scanner.nextInt();
        Integral Hilo1 = new Integral(f1, puntos, 1);
        Integral Hilo2 = new Integral(f2, puntos, 2);
        Hilo1.start();
        Hilo2.start();
    }
}

class Integral extends Thread
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

    public void run()
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
        System.out.println("El valor de la integral es: " + (double) puntos_validos/puntos);
    }
}