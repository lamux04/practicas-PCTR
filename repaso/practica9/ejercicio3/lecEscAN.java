package practica9.ejercicio3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class lecEscAN {
    ReentrantLock cerrojo = new ReentrantLock();
    Condition lectores = cerrojo.newCondition();
    Condition escritores = cerrojo.newCondition();

    boolean escribiendo = false;
    int nL = 0;
    int nLe = 0;

    void abrir_lectura() {
        cerrojo.lock();
        nLe++;
        while (escribiendo)
            try {
                lectores.await();
            } catch (InterruptedException e) {
            }
        nLe--;
        nL++;
        lectores.signal();
        cerrojo.unlock();
    }
    
    void cerrar_lectura() {
        cerrojo.lock();
        nL--;
        if (nL == 0)
            escritores.signal();
        cerrojo.unlock();
    }

    void abrir_escritura() {
        cerrojo.lock();
        while (nL > 0 || escribiendo)
            try {
                escritores.await();
            } catch (InterruptedException e) {
            }
        escribiendo = true;
        cerrojo.unlock();
    }
    
    void cerrar_escritura() {
        cerrojo.lock();
        escribiendo = false;
        if (nLe > 0)
            lectores.signal();
        else
            escritores.signal();
        cerrojo.unlock();
    }
}
