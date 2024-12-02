package practica9;

import java.util.concurrent.*;

public class usoccSem {
    
    static public class Titular implements Runnable {
        ccSem cuenta;
        int tipo;

        public Titular (ccSem cuenta, int tipo) {
            this.cuenta = cuenta;
            this.tipo = tipo;
        }

        public void run() {
            if (tipo % 2 == 0)
                for (int i = 0; i < 10; ++i)
                {
                    try {
                        cuenta.ingreso(i * 4);
                        System.out.println("Ingreso de " + i * 4);
                    } catch (InterruptedException e) {}
                }
            else
                for (int i = 0; i < 10; ++i)
                {
                    try {
                        cuenta.reintegro(i * 4);
                        System.out.println("Reintegro de " + i*4);
                    } catch (InterruptedException e) {
                    }
                }
            
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(8);
        ccSem cuenta = new ccSem();

        for (int i = 0; i < 10; ++i)
        {
            executor.submit(new Titular(cuenta, i));
        }
        
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("Total: " + cuenta.dinero);
    }


}

