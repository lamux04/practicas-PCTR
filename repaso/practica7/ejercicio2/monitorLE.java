package practica7.ejercicio2;

public class monitorLE {
    int nL = 0;
    boolean escribiendo = false;

    public synchronized void abrir_lectura() {
        while (escribiendo)
        try {
            wait();
        }
        catch (InterruptedException e) {
        }
        nL++;
    }

    public synchronized void cerrar_lectura() {
        nL--;
        if (nL == 0)
            notifyAll();
    }

    public synchronized void abrir_escritura() {
        while (nL > 0 || escribiendo)
        try {
            wait();
        } catch (InterruptedException e) {
        }
        escribiendo = true;
    }

    public synchronized void cerrar_escritura() {
        escribiendo = false;
        notifyAll();
    }
}
