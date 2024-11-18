public class monitorLE 
{
    int nl = 0;
    boolean escribiendo;

    synchronized void abrir_lectura() throws InterruptedException
    {
        while (escribiendo)
            wait();
        nl++;
        notifyAll();
    }

    synchronized void cerrar_lectura() throws InterruptedException
    {
        nl--;
        if (nl == 0)
            notifyAll();
    }
    
    synchronized void abrir_escritura() throws InterruptedException
    {
        while (nl != 0 || escribiendo)
            wait();
        escribiendo = true;
    }

    synchronized void cerrar_escritura() throws InterruptedException
    {
        escribiendo = false;
        notifyAll();
    }
}
