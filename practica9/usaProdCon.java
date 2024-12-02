package practica9;

public class usaProdCon 
{
    
    public static void main(String[] args) throws InterruptedException
    {
        prodConAN monitor = new prodConAN();
        Thread hilo[] = new Thread[30];
        for (int i = 0; i < 30; ++i)
        {
            if (i % 2 == 0)
                hilo[i] = new Consumidor(monitor);
            else
                hilo[i] = new Productor(monitor, i);
            hilo[i].start();
        }

        for (int i = 0; i < 30; ++i)
        {
            while (hilo[i] == null)
                ;
            hilo[i].join();
        }

        System.out.println(monitor.buffer);
    }
}

class Productor extends Thread 
{
    prodConAN monitor;
    int v;

    public Productor(prodConAN monitor, int v) 
    {
        this.monitor = monitor;
        this.v = v;
    }

    public void run() 
    {
        try {
            monitor.producir(v);
            System.out.println("Producimos el valor: " + v);
        } catch (InterruptedException e) {}
    }
}

class Consumidor extends Thread {
    prodConAN monitor;

    public Consumidor(prodConAN monitor) {
        this.monitor = monitor;
    }

    public void run() {
        try {
            System.out.println("Sacamos el valor: " + monitor.consumir());
        } catch (InterruptedException e) {}
    }
}