package practica8.ejercicio3;

public class DiningPhilosophersMonitor {
    private enum State { THINKING, HUNGRY, EATING }
    private final State[] states; // Estados de los filósofos
    private final int numPhilosophers;

    public DiningPhilosophersMonitor(int numPhilosophers) {
        this.numPhilosophers = numPhilosophers;
        states = new State[numPhilosophers];
        for (int i = 0; i < numPhilosophers; i++) {
            states[i] = State.THINKING; // Todos comienzan pensando
        }
    }

    private int left(int i) {
        return (i + numPhilosophers - 1) % numPhilosophers; // Filósofo a la izquierda
    }

    private int right(int i) {
        return (i + 1) % numPhilosophers; // Filósofo a la derecha
    }

    public synchronized void takeForks(int philosopherId) throws InterruptedException {
        states[philosopherId] = State.HUNGRY; // El filósofo está hambriento
        test(philosopherId); // Intenta tomar los tenedores
        while (states[philosopherId] != State.EATING) { // Espera si no puede comer
            wait();
        }
    }

    public synchronized void putForks(int philosopherId) {
        states[philosopherId] = State.THINKING; // El filósofo deja de comer
        test(left(philosopherId)); // Verifica si el filósofo de la izquierda puede comer
        test(right(philosopherId)); // Verifica si el filósofo de la derecha puede comer
        notifyAll(); // Notifica a todos los hilos
    }

    private void test(int philosopherId) {
        if (states[philosopherId] == State.HUNGRY &&
            states[left(philosopherId)] != State.EATING &&
            states[right(philosopherId)] != State.EATING) {
            states[philosopherId] = State.EATING; // Puede comer
            notifyAll(); // Notifica a los hilos en espera
        }
    }
}
