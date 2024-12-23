package practica9.ejercicio4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class prodConAN {
    ReentrantLock cerrojo = new ReentrantLock();
    Condition nolleno = cerrojo.newCondition();
    Condition novacio = cerrojo.newCondition();

    final int N = 10;
    int[] buffer = new int[N];
    int n = 0;
    int frente = 0, cola = 0;

    void producir(int x) {
        cerrojo.lock();
        while (n == N)
            try {
                nolleno.await();
            } catch (InterruptedException e) {
            }
        n++;
        buffer[cola] = x;
        cola = (cola + 1) % N;
        novacio.signal();
        cerrojo.unlock();
    }
    
    int consumir() {
        int x;
        cerrojo.lock();
        while (n == 0)
        try {
            novacio.await();
        } catch (InterruptedException e) {
        }
        n++;
        x = buffer[frente];
        frente = (frente + 1) % N;
        nolleno.signal();
        cerrojo.unlock();
        return x;
    }
}
