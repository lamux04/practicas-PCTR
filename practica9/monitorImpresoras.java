package practica9;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class monitorImpresoras {
    boolean[] impresoras = new boolean[3];
    int disponibles = 3;

    ReentrantLock cerrojo = new ReentrantLock();
    Condition procesos = cerrojo.newCondition();

    public monitorImpresoras() {
        disponibles = 3;
        for (int i = 0; i < 3; ++i)
            impresoras[i] = true;
    }
    
    int pedirImpresora() {
        cerrojo.lock();
        while (disponibles == 0)
        {
            try {
                procesos.await();
            } catch (InterruptedException e) {}
        }

        int i = 0;
        while (!impresoras[i])
            ++i;
        impresoras[i] = false;
        disponibles--;
        cerrojo.unlock();
        return i;
    }

    void liberarImpresora(int n) {
        cerrojo.lock();
        impresoras[n] = true;
        disponibles++;
        procesos.signalAll();
        cerrojo.unlock();
    }
}
