package practica8;

import java.util.concurrent.*;

public class usoCuentaCompartida {
    
    static public class Titular implements Runnable {
        cuentaCompartida cuenta;
        int tipo;

        public Titular (cuentaCompartida cuenta, int tipo) {
            this.cuenta = cuenta;
            this.tipo = tipo;
        }

        public void run() {
            if (tipo % 2 == 0)
                for (int i = 0; i < 10; ++i)
                {
                    cuenta.ingreso(i * 4);
                    System.out.println("Ingreso de " + i * 4);
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
        cuentaCompartida cuenta = new cuentaCompartida();

        for (int i = 0; i < 10; ++i)
        {
            executor.submit(new Titular(cuenta, i));
        }
        
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("Total: " + cuenta.dinero);
    }


}

