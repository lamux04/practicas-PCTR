public class deadlock
{
    public static void main(String[] args) throws InterruptedException
    {
        Integer cerrojoA = 1, cerrojoB = 2, cerrojoC = 3;
        hilo1 h1 = new hilo1(cerrojoA, cerrojoB, cerrojoC);
        hilo2 h2 = new hilo2(cerrojoA, cerrojoB, cerrojoC);
        hilo3 h3 = new hilo3(cerrojoA, cerrojoB, cerrojoC);

        h1.start();
        h2.start();
        h3.start();

        h1.join();
        h2.join();
        h3.join();

        System.out.println("Hola mundo");
    }
}

class hilo1 extends Thread
{
    Integer cerrojoA, cerrojoB, cerrojoC;

    public hilo1 (Integer cerrojoA, Integer cerrojoB, Integer cerrojoC)
    {
        this.cerrojoA = cerrojoA;
        this.cerrojoB = cerrojoB;
        this.cerrojoC = cerrojoC;
    }

    public void run(){
        synchronized (cerrojoA)
        {
            synchronized (cerrojoB)
            {
                synchronized (cerrojoC)
                {
                    System.out.println("Hilo 1");
                }
            }
        }
    }
}
class hilo2 extends Thread
{
    Integer cerrojoA, cerrojoB, cerrojoC;

    public hilo2 (Integer cerrojoA, Integer cerrojoB, Integer cerrojoC)
    {
        this.cerrojoA = cerrojoA;
        this.cerrojoB = cerrojoB;
        this.cerrojoC = cerrojoC;
    }

    public void run(){
        synchronized (cerrojoB)
        {
            synchronized (cerrojoA)
            {
                synchronized (cerrojoC)
                {
                    System.out.println("Hilo 2");
                }
            }
        }
    }
}

class hilo3 extends Thread
{
    Integer cerrojoA, cerrojoB, cerrojoC;

    public hilo3 (Integer cerrojoA, Integer cerrojoB, Integer cerrojoC)
    {
        this.cerrojoA = cerrojoA;
        this.cerrojoB = cerrojoB;
        this.cerrojoC = cerrojoC;
    }

    public void run(){
        synchronized (cerrojoC)
        {
            synchronized (cerrojoB)
            {
                synchronized (cerrojoA)
                {
                    System.out.println("Hilo 3");
                }
            }
        }
    }
}

    