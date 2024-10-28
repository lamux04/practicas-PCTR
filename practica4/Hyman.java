public class Hyman {

    /* Process p wants to enter critical section */
    static volatile boolean wantp = false;
    /* Process q wants to enter critical section */    
    static volatile boolean wantq = false;
    /* Which processes turn is it */
    static volatile int turn = 0;

    static int contador = 0;

    class P extends Thread 
    {
        public void run()
        {
            for (int i = 0; i < 1000; ++i) {
                wantp = true;
                while (turn != 0) {
                    while (wantp) {
                    }
                    turn = 0;
                }
                // SC
                contador++;
                wantp = false;
            }
        }
    }
    
    class Q extends Thread 
    {
        public void run() 
        {
            for (int i = 0; i < 1000; ++i) {
                wantq = true;
                while (turn != 1) {
                    while (wantq) {
                    }
                    turn = 1;
                }
                // SC
                contador++;
                wantq = false;
            }
        }
    }

    Hyman() throws InterruptedException
    {
        Thread p = new P();
        Thread q = new Q();
        p.start();
        q.start();
        p.join();
        q.join();
    }
    
    public static void main(String[] args) {
        try
        {
            new Hyman();
        } catch (InterruptedException e)
        {
        }
        System.out.println(contador);
    }
}
