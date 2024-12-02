package practica9;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ccSem {
    public double dinero = 0;
    Semaphore em;

    public ccSem() {
        em = new Semaphore(1);
    }

    void ingreso (double cuantia) throws InterruptedException
    {
        em.acquire();
        dinero += cuantia;
        em.release();
    }

    double reintegro (double cuantia) throws InterruptedException
    {
        em.acquire();
        while (dinero < cuantia)
        {
            em.release();
            em.acquire();
        }    
        dinero -= cuantia;
        em.release();
        return cuantia;
    }
}
