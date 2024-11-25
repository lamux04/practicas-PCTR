package practica8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class usaMonitorImpresoras {
    public static class tarea implements Runnable {
        monitorImpresoras impresoras;

        public tarea (monitorImpresoras impresoras)
        {
            this.impresoras = impresoras;
        }

        public void run() {
            for (int i = 0; i < 10; ++i)
            {
                int imp = impresoras.pedirImpresora();
                System.out.println(Thread.currentThread().getName() + " usa la impresora " + imp);
                Thread.yield();
                impresoras.liberarImpresora(imp);
                System.out.println(Thread.currentThread().getName() + " libera la impresora " + imp);

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(8);
        monitorImpresoras impresoras = new monitorImpresoras();

        for (int i = 0; i < 20; ++i)
            executor.submit(new tarea(impresoras));

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.DAYS);
    }
}
