import java.util.Scanner;

public class NewtonRaphson {
    
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int iteraciones = 50;
        double aproximacion = Math.random();

        Thread Hilo1 = new Thread(new Funcion1(iteraciones, aproximacion));
        Hilo1.start();
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
        for (int i = 0; i < iteraciones; ++i)
        {
            aproximacion = aproximacion - (Math.cos(aproximacion) - aproximacion*aproximacion*aproximacion)/(-Math.sin(aproximacion) - 3*aproximacion*aproximacion);
            System.out.println("Aproximacion: " + i + ": " + aproximacion);
        }
    }
}