package practica8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class usaMonitorFilosofos {
    public static class filosofo implements Runnable {
        monitorFilosofos filosofos;
        int id;

        public filosofo (monitorFilosofos filosofos, int id)
        {
            this.filosofos = filosofos;
            this.id = id;
        }

        public void run() {
            for (int i = 0; i < 10; ++i)
            {
                try {
                    filosofos.pickUpForks(id);
                } catch (InterruptedException e)
                {}
                System.out.println("Filosofo " + id + " comiendo...");
                filosofos.putDownForks(id);
                System.out.println("Filosofo " + id + " pensando...");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(8);
        monitorFilosofos filosofos = new monitorFilosofos(5);

        for (int i = 0; i < 5; ++i)
            executor.submit(new filosofo(filosofos, i));

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.DAYS);
    }
}
