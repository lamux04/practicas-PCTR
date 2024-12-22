package practica4.ejercicio3y4;

class First {
    /* Number of processes currently in critical section */
    static volatile int inCS = 0;
    /* Which processes turn is it */
    static volatile int turn = 1;

    class P extends Thread {
        public void run() {
            for (int i = 0; i < 10000000; ++i) {
                /* Non-critical section */
                while (turn != 1)
                    Thread.yield();
                inCS++;
                turn = 2;
            }
        }
    }
    
    class Q extends Thread {
        public void run() {
            for (int i = 0; i < 10000000; ++i) {
                /* Non-critical section */
                while (turn != 2)
                    Thread.yield();
                inCS--;
                turn = 1;
            }
        }
    }

    First() {
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
        new First();
    }
}