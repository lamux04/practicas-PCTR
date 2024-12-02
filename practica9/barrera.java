package practica9;

import java.util.concurrent.CyclicBarrier;

public class barrera extends Thread {
    static CyclicBarrier barrier = new CyclicBarrier(3);

    public void run () {
        try {
            barrier.await();
            System.out.println(getName());
        } catch (Exception e) {}
    }
    
    public static void main(String[] args) {
        new barrera().start();
        new barrera().start();
        new barrera().start();
    }
}