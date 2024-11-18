public class usaProdCon 
{
    
    public static void main(String[] args) throws InterruptedException
    {
        PCMonitor monitor = new PCMonitor();
        Thread hilo[] = new Thread[30];
        hilo[0] = new Consumidor(monitor);
        hilo[0].start();
        for (int i = 1; i < 30; ++i)
        {
            hilo[i] = new Productor(monitor, i);
            hilo[i].start();
        }

        for (int i = 0; i < 30; ++i)
        {
            while (hilo[i] == null)
                ;
            hilo[i].join();
        }

        System.out.println(monitor.Buffer);
    }
}

class Productor extends Thread 
{
    PCMonitor monitor;
    int v;

    public Productor(PCMonitor monitor, int v) 
    {
        this.monitor = monitor;
        this.v = v;
    }

    public void run() 
    {
        monitor.Append(v);
        System.out.println("Producimos el valor: " + v);
    }
}

class Consumidor extends Thread {
    PCMonitor monitor;

    public Consumidor(PCMonitor monitor) {
        this.monitor = monitor;
    }

    public void run() {
        System.out.println("Sacamos el valor: " + monitor.Take());
    }
}