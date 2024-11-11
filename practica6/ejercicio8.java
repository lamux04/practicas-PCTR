public class ejercicio8 {
    public static void main(String[] args) throws InterruptedException {
        Entero num = new Entero();
        Thread hilo1 = new Thread(new Clase1(num));
        Thread hilo2 = new Thread(new Clase2(num));

        hilo1.start();
        hilo2.start();

        hilo1.join();
        hilo2.join();

        System.out.println(num.ver());

    }
}

class Entero
{
    int num = 0;

    synchronized void incrementar()
    {
        num++;
    }

    synchronized int ver()
    {
        return num;
    }
}

class Clase1 implements Runnable
{
    Entero num;

    public Clase1 (Entero num)
    {
        this.num = num;
    }

    public void run()
    {
        for (int i = 0; i < 1000; ++i)
        {
            num.incrementar();
        }
    }
}

class Clase2 implements Runnable
{
    Entero num;

    public Clase2 (Entero num)
    {
        this.num = num;
    }

    public void run()
    {
        for (int i = 0; i < 1000; ++i)
        {
            num.incrementar();
        }
    }
}