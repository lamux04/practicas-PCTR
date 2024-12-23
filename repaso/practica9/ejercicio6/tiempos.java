package practica9.ejercicio6;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class tiempos {
    public static void main(String[] args) {
        long iteracionse = 100000000;
        System.out.println("Tiempo con synchronized -> " + conSynchronized(iteracionse) / 10e9);
        System.out.println("Tiempo con semáforos -> " + conSemaforos(iteracionse) / 10e9);
        System.out.println("Tiempo con Lock -> " + conLock(iteracionse) / 10e9);
        System.out.println("Tiempo con Atomic -> " + conAtomic(iteracionse) / 10e9);
    }
    
    public static long conSynchronized(long iter) {
        int n = 0;
        final long inicial = System.nanoTime();
        for (long i = 0; i < iter; ++i) {
            synchronized (tiempos.class) {
                n++;
            }
        }
        final long fin = System.nanoTime();
        return (fin - inicial);
    }
    
    public static long conSemaforos(long iter) {
        int n = 0;
        final long inicial = System.nanoTime();
        Semaphore semaforo = new Semaphore(1);
        for (long i = 0; i < iter; ++i) {
            try {
                semaforo.acquire();
            } catch (InterruptedException e) {
            }
            n++;
            semaforo.release();
        }
        final long fin = System.nanoTime();
        return (fin - inicial);
    }
    
    public static long conLock(long iter) {
        int n = 0;
        final long inicial = System.nanoTime();
        ReentrantLock cerrojo = new ReentrantLock();
        for (long i = 0; i < iter; ++i) {
            cerrojo.lock();
            n++;
            cerrojo.unlock();
        }
        final long fin = System.nanoTime();
        return (fin - inicial);
    }

    public static long conAtomic(long iter) {
        final long inicial = System.nanoTime();
        AtomicInteger n = new AtomicInteger(0);
        for (long i = 0; i < iter; ++i)
        {
            n.incrementAndGet();
        }
        final long fin = System.nanoTime();
        return (fin - inicial);
    }
}
