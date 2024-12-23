package practica9.ejercicio2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class barrera {
    static class hebra extends Thread {
        private CyclicBarrier barrera;

        public hebra(CyclicBarrier barrera) {
            this.barrera = barrera;
        }

        public void run() {
            while (true) {
                try {
                    barrera.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                }
                System.out.println(getName() + " sale de la barrera ");   
            }
        }
    }

    public static void main(String[] args) {
        CyclicBarrier barrera = new CyclicBarrier(3);
        new hebra(barrera).start();
        new hebra(barrera).start();
        new hebra(barrera).start();
    }
}
