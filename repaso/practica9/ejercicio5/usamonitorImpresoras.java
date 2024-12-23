package practica9.ejercicio5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class usamonitorImpresoras implements Runnable {
    private monitorImpresoras monitor;

    public usamonitorImpresoras(monitorImpresoras monitor) {
        this.monitor = monitor;
    }

    public void run() {
        for (int i = 0; i < 1000; ++i)
        {
            int n = monitor.pedirImpresora();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {}
            System.out.println("Hilo " + Thread.currentThread().getName() + " -> Impresion " + i + " con impresora " + n);
            monitor.liberarImpresora(n);
        }   
    }

    public static void main(String[] args) {
        monitorImpresoras monitor = new monitorImpresoras();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        
        executor.submit(new usamonitorImpresoras(monitor));
        executor.submit(new usamonitorImpresoras(monitor));
        executor.submit(new usamonitorImpresoras(monitor));
        executor.submit(new usamonitorImpresoras(monitor));
        executor.submit(new usamonitorImpresoras(monitor));

        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.DAYS);
        } catch (InterruptedException e) {}
        executor.close();
    }
}
