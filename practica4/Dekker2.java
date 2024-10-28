/* Copyright (C) 2006 M. Ben-Ari. See copyright.txt */
/* Programmed by Panu Pitk�m�ki */

/* Dekker's algorithm */
class Dekker2 {
    /* Number of processes currently in critical section */
    static volatile int inCS = 0;
    /* Process p wants to enter critical section */
    static volatile boolean wantp = false;
    /* Process q wants to enter critical section */    
    static volatile boolean wantq = false;
    /* Which processes turn is it */
    static volatile int turn = 1;

    static int contador = 0;

    class P extends Thread {
        public void run() {
            for (int i = 0; i < 100000; ++i) {
                /* Non-critical section */
                wantp = true;
                while (wantq) {
                    if (turn == 2) {
                        wantp = false;
                        while (turn == 2)
                            Thread.yield();
                        wantp = true;
                    }
                }
                inCS++;
                Thread.yield();
                contador++;
                inCS--;
                turn = 2;
                wantp = false;
            }
        }
    }
    
    class Q extends Thread {
        public void run() {
            for (int i = 0; i < 100000; ++i) {
                /* Non-critical section */
                wantq = true;
                while (wantp) {
                    if (turn == 1) {
                        wantq = false;
                        while (turn == 1)
                            Thread.yield();
                        wantq = true;
                    }
                }
                inCS++;
                Thread.yield();
                contador++;
                inCS--;
                turn = 1;
                wantq = false;
            }
        }
    }

    Dekker2() throws InterruptedException {
        Thread p = new P();
        Thread q = new Q();
        p.start();
        q.start();

        p.join();
        q.join();

    }
    
    public static void main(String[] args) {
        try {
            new Dekker2();
        } catch (InterruptedException e)
        {}
        System.out.println(contador);
    }
}