package practica9;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class monitorFilosofos {
    final int N = 5;

    enum tEstado {
        pensando, comiendo, hambriento
    };
    
    tEstado[] estado;
    ReentrantLock cerrojo = new ReentrantLock();
    Condition[] dormir;

    public monitorFilosofos() {
        for (int i = 0; i < N; ++i)
        {
            dormir[i] = cerrojo.newCondition();
            estado[i] = tEstado.pensando;
        }    
    }

    public void coger_palillos(int i) throws InterruptedException
    {
        cerrojo.lock();
        estado[i] = tEstado.hambriento;
        verificar(i);

        while (estado[i] != tEstado.comiendo)
            dormir[i].await();

        cerrojo.unlock();
    }

    public void soltar_palillos(int i) throws InterruptedException
    {
        cerrojo.lock();
        estado[i] = tEstado.pensando;

        verificar(i + 1);
        verificar((i + N - 1) % N);

        cerrojo.unlock();
    }
    

    public void verificar(int k)
    {
        cerrojo.lock();
        if (estado[(k + N - 1) % N] != tEstado.comiendo && estado[(k + 1) % N] != tEstado.comiendo
                && estado[k] == tEstado.hambriento)
        {
            estado[k] = tEstado.comiendo;
            dormir[k].signal();
        }
        cerrojo.unlock();
    }
}