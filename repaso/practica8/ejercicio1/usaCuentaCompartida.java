package practica8.ejercicio1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class usaCuentaCompartida {
    static class MeterDinero implements Runnable {
        cuentaCompartida monitor;

        public MeterDinero(cuentaCompartida monitor) {
            this.monitor = monitor;
        }

        public void run() {
            for (int i = 0; i < 100000; ++i)
                monitor.ingreso(i);
        }
    }
    static class SacarDinero implements Runnable {
        cuentaCompartida monitor;

        public SacarDinero(cuentaCompartida monitor) {
            this.monitor = monitor;
        }

        public void run() {
            for (int i = 0; i < 100000; ++i)   
                monitor.reintegro(i);
        }
    }
    public static void main(String[] args) {
        cuentaCompartida monitor = new cuentaCompartida();
        ExecutorService executor = new ThreadPoolExecutor(4, 8, 100, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>());
        
        executor.submit(new SacarDinero(monitor));
        executor.submit(new MeterDinero(monitor));
        executor.submit(new MeterDinero(monitor));
        executor.submit(new MeterDinero(monitor));
        executor.submit(new MeterDinero(monitor));
        executor.submit(new SacarDinero(monitor));
        executor.submit(new SacarDinero(monitor));

        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.DAYS);
        } catch (InterruptedException e) {}
        executor.close();

        System.out.println("Saldo -> " + monitor.consultar());
    }
}
