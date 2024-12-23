package practica9.ejercicio1;

import java.util.concurrent.Semaphore;

public class ccSem {
    private double saldo = 0;
    Semaphore em = new Semaphore(1);

    void ingreso(double cuantia) {
        try {
            em.acquire();
        } catch (InterruptedException e) {}
        saldo += cuantia;
        em.release();
    }

    void reintegro(double cuantia) {
        try {
            em.acquire();
        } catch (InterruptedException e) {
        }
        while (cuantia > saldo)
        {
            em.release();
            try {
                em.acquire();
            } catch (InterruptedException e) {
            }
        }
        saldo -= cuantia;
        em.release();
    }
    
    double consultar() {
        try {
            em.acquire();
        } catch (InterruptedException e) {
        }
        double saldo = this.saldo;
        em.release();
        return saldo;
    }
}
