package practica9;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class prodConAN {
    final int N = 5;
    public int[] buffer = new int[N];
    int prinipio = 0;
    int fin = 0;
    int n = 0;

    ReentrantLock cerrojo = new ReentrantLock();
    Condition productores = cerrojo.newCondition(),
            consumidores = cerrojo.newCondition();

    void producir (int x) throws InterruptedException
    {
        cerrojo.lock();
        while (n == N)
        {
            productores.await();
        }
        buffer[fin] = x;
        fin = (fin + 1) % N;
        n++;
        consumidores.signalAll();
        cerrojo.unlock();
    }
    
    int consumir () throws InterruptedException
    {
        cerrojo.lock();
        while (n == 0)
        {
            consumidores.await();
        }
        int x = buffer[prinipio];
        prinipio = (prinipio + 1) % N;
        n--;
        productores.signalAll();
        cerrojo.unlock();
        return x;
    }


}
