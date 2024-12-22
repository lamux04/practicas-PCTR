import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class usaProdCon {
    static class Productor implements Runnable {
        PCMonitor monitor;

        public Productor(PCMonitor monitor) {
            this.monitor = monitor;
        }

        public void run() {
            for (int i = 0; i < 9; ++i)
                monitor.Append(i);
        }
    }
    
    static class Consumidor implements Runnable {
        PCMonitor monitor;

        public Consumidor(PCMonitor monitor) {
            this.monitor = monitor;
        }

        public void run() {
            for (int i = 0; i < 3; ++i) {
                System.out.println("Tomamos -> " + monitor.Take());
            }
        }
    }
    
    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(4, 8, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        PCMonitor monitor = new PCMonitor();
        executor.submit(new Productor(monitor));
        executor.submit(new Consumidor(monitor));
        executor.submit(new Consumidor(monitor));
        executor.submit(new Consumidor(monitor));

        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.DAYS);
        } catch (InterruptedException e) {
        }
        executor.close();
    }
}
