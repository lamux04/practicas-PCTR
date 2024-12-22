package practica4.ejercicio7;

public class Hyman {
    /* Number of processes currently in critical section */
    static volatile int inCS = 0;
    /* Which processes turn is it */
    static volatile int turn = 1;
    boolean c0 = false;
    boolean c1 = false;

    class P extends Thread {
        public void run() {
            for (int i = 0; i < 10000000; ++i) {
                c0 = true;
                while (turn != 0)
                {
                    while (c1)
                        Thread.yield();
                    turn = 0;
                }
                inCS++;
                c0 = false;
            }
        }
    }
    
    class Q extends Thread {
        public void run() {
            for (int i = 0; i < 10000000; ++i) {
                c1 = true;
                while (turn != 1)
                {
                    while (c0)
                        Thread.yield();
                    turn = 1;
                }
                inCS--;
                c1 = false;
            }
        }
    }

    Hyman() {
        Thread p = new P();
        Thread q = new Q();
        p.start();
        q.start();
        try {
            p.join();
            q.join();
        } catch (InterruptedException e) {
        }

        System.out.println(inCS);
    }

    public static void main(String[] args) {
        new Hyman();
    }
}
