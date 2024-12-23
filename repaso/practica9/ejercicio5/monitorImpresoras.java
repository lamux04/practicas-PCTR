package practica9.ejercicio5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class monitorImpresoras {
    private final int N = 3;
    private int n = 3;
    private ReentrantLock cerrojo = new ReentrantLock();
    private boolean[] libre = new boolean[N];
    private Condition con = cerrojo.newCondition();


    public monitorImpresoras() {
        for (int i = 0; i < N; ++i)
        {
            libre[i] = true;
        }    
    }

    int pedirImpresora() {
        cerrojo.lock();
        while (n == 0)
            try {
                con.await();
            } catch (InterruptedException e) {
            }

        int i = 0;
        while (!libre[i])
            i++;
        libre[i] = false;
        n--;
        cerrojo.unlock();
        return i;
    }
    
    void liberarImpresora(int i) {
        cerrojo.lock();
        libre[i] = true;
        n++;
        con.signal();
        cerrojo.unlock();
    }
}
