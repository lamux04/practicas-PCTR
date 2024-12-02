package practica9;

public class usalecEscAn 
{
    
    public static void main(String[] args) throws InterruptedException
    {
        lecEscAn monitor = new lecEscAn();
        Thread hilo[] = new Thread[30];
        for (int i = 0; i < 15; ++i)
        {
            hilo[i] = new Escritor(monitor);
            hilo[i].start();
            hilo[i + 15] = new Lector(monitor);
            hilo[i + 15].start();
        }
    }
}

class Escritor extends Thread 
{
    lecEscAn monitor;

    public Escritor(lecEscAn monitor) 
    {
        this.monitor = monitor;
    }

    public void run() 
    {
        try {
            monitor.abrir_escritura();
            System.out.println("Escribiendo...");
            sleep(1000);
            System.out.println("Terminado de escribir");
            monitor.cerrar_escritura();
        } catch (InterruptedException e) {}
    }
}

class Lector extends Thread 
{
    lecEscAn monitor;

    public Lector(lecEscAn monitor) 
    {
        this.monitor = monitor;
    }

    public void run() 
    {
        try {
            monitor.abrir_lectura();
            System.out.println("Leyendo..." + getName());
            sleep(1000);
            System.out.println("Terminado de leer" + getName());
            monitor.cerrar_lectura();
        } catch (InterruptedException e) {}
    }
}