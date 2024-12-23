package practica9.ejercicio1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class cCRL {
    private double saldo = 0;
    ReentrantLock cerrojo = new ReentrantLock();
    Condition condicion = cerrojo.newCondition();

    void ingreso(double cuantia) {
        cerrojo.lock();
        saldo += cuantia;
        condicion.signal();
        cerrojo.unlock();
    }

    void reintegro(double cuantia) {
        cerrojo.lock();
        while (cuantia > saldo)
            try {
                condicion.await();
            } catch (InterruptedException e) {
            }
        saldo -= cuantia;
        condicion.signal();
        cerrojo.unlock();
    }
    
    double consultar() {
        cerrojo.lock();
        double saldo = this.saldo;
        cerrojo.unlock();
        return saldo;
    }
}
