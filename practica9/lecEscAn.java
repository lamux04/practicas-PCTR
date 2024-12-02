package practica9;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class lecEscAn 
{
    int nl = 0, nLectoresEsperando = 0;
    boolean escribiendo;
    ReentrantLock cerrojo;
    Condition cEscritores;
    Condition cLectores;

    public lecEscAn() {
        cerrojo = new ReentrantLock();
        cEscritores = cerrojo.newCondition();
        cLectores = cerrojo.newCondition();

    }

    void abrir_lectura() throws InterruptedException
    {
        cerrojo.lock();
        nLectoresEsperando++;
        while (escribiendo)
        {
            cLectores.await();
        }
        nLectoresEsperando--;
        nl++;
        cLectores.signalAll();
        cerrojo.unlock();
    }

    void cerrar_lectura() throws InterruptedException
    {
        cerrojo.lock();
        nl--;
        if (nl == 0)
            cEscritores.signalAll();
        cerrojo.unlock();
    }
    
    void abrir_escritura() throws InterruptedException
    {
        cerrojo.lock();
        while (nl != 0 || escribiendo)
            cEscritores.await();
        escribiendo = true;
        cerrojo.unlock();
    }

    void cerrar_escritura() throws InterruptedException
    {
        cerrojo.lock();
        escribiendo = false;
        if (nLectoresEsperando > 0)
            cLectores.signalAll();
        else
            cEscritores.signalAll();
        cerrojo.unlock();
    }
}
