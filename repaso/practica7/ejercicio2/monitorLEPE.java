package practica7.ejercicio2;

public class monitorLEPE {
    int nL = 0, nEe = 0;
    boolean escribiendo = false;

    public synchronized void abrir_lectura() {
        while (escribiendo || nEe > 0)
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
        nEe++;
        while (nL > 0 || escribiendo)
        try {
            wait();
        } catch (InterruptedException e) {
        }
        nEe--;
        escribiendo = true;
    }

    public synchronized void cerrar_escritura() {
        escribiendo = false;
        notifyAll();
        // Se produce inanción en la lectura, habría que intentar despertar a los lectores que esten esperando en este punto
    }
}
