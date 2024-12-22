package practica8.ejercicio3;

public class usaFilosofos {
    public static void main(String[] args) {
        int numPhilosophers = 5;
        DiningPhilosophersMonitor monitor = new DiningPhilosophersMonitor(numPhilosophers);

        // Crear hilos para cada filósofo
        Thread[] philosophers = new Thread[numPhilosophers];
        for (int i = 0; i < numPhilosophers; i++) {
            int id = i;
            philosophers[i] = new Thread(() -> {
                try {
                    while (true) {
                        System.out.println("Filósofo " + id + " está pensando.");
                        Thread.sleep((long) (Math.random() * 1000));
                        System.out.println("Filósofo " + id + " tiene hambre.");
                        monitor.takeForks(id); // Intentar tomar los tenedores
                        System.out.println("Filósofo " + id + " está comiendo.");
                        Thread.sleep((long) (Math.random() * 1000));
                        monitor.putForks(id); // Dejar los tenedores
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Iniciar los hilos
        for (Thread philosopher : philosophers) {
            philosopher.start();
        }
    }
}
