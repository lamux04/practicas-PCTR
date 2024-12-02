package practica9;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class cCRL {
    public double dinero = 0;
    ReentrantLock cerrojo;
    Condition sinDinero;

    public cCRL() {
        cerrojo = new ReentrantLock();
        sinDinero = cerrojo.newCondition();
    }

    void ingreso (double cuantia)
    {
        cerrojo.lock();
        dinero += cuantia;
        sinDinero.signalAll();
        cerrojo.unlock();
    }

    double reintegro (double cuantia) throws InterruptedException
    {
        cerrojo.lock();
        while (dinero < cuantia)
        {
            sinDinero.await();
        }
        dinero -= cuantia;
        cerrojo.unlock();
        return cuantia;
    }
}
