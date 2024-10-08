import java.util.Scanner;

public class NewtonRaphson {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el numero de iteraciones de la primera funcion: ");
        int iteraciones1 = scanner.nextInt();
        System.out.print("Introduce la aproximacion lineal de la primera funcion: ");
        double aproximacion1 = scanner.nextDouble();
        System.out.print("Introduce el numero de iteraciones de la primera funcion: ");
        int iteraciones2 = scanner.nextInt();
        System.out.print("Introduce la aproximacion lineal de la primera funcion: ");
        double aproximacion2 = scanner.nextDouble();

        Thread Hilo1 = new Thread(new Funcion1(iteraciones1, aproximacion1));
        Thread Hilo2 = new Thread(new Funcion2(iteraciones2, aproximacion2));
        Hilo1.start();
        Hilo2.start();

        try
        {
            Hilo1.join();
            Hilo2.join();
        } catch (InterruptedException e)
        {
        }
        System.out.println("Ejecucion finalizada");
        scanner.close();
    }

    
}

class Funcion1 implements Runnable 
{
    double aproximacion;
    int iteraciones;
    Funcion1(int iteraciones, double aproximacion) {
        this.iteraciones = iteraciones;
        this.aproximacion = aproximacion;
    }

    public void run()
    {
        for (int i = 1; i <= iteraciones; ++i)
        {
            aproximacion = aproximacion - (Math.cos(aproximacion) - aproximacion*aproximacion*aproximacion)/(-Math.sin(aproximacion) - 3*aproximacion*aproximacion);
            System.out.println("Aproximacion funcion 1 iteracion " + i + ": " + aproximacion);
        }
    }
}

class Funcion2 implements Runnable
{
    double aproximacion;
    int iteraciones;

    Funcion2(int iteraciones, double aproximacion)
    {
        this.iteraciones = iteraciones;
        this.aproximacion = aproximacion;
    }

    public void run()
    {
        for (int i = 1; i <= iteraciones; ++i)
        {
            aproximacion = aproximacion - (aproximacion * aproximacion - 5) / (2 * aproximacion);
            System.out.println("Aproximacion funcion 2 iteracion " + i + ": " + aproximacion);
        }
    }
}