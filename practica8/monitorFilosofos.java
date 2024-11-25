package practica8;

class monitorFilosofos {
    private final boolean[] forks; // Representa si un tenedor está disponible

    public monitorFilosofos(int numPhilosophers) {
        forks = new boolean[numPhilosophers];
        // Inicialmente, todos los tenedores están disponibles
        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = true;
        }
    }

    public synchronized void pickUpForks(int philosopher) throws InterruptedException {
        int leftFork = philosopher;
        int rightFork = (philosopher + 1) % forks.length;

        // Espera hasta que ambos tenedores estén disponibles
        while (!forks[leftFork] || !forks[rightFork]) {
            wait();
        }

        // Toma ambos tenedores
        forks[leftFork] = false;
        forks[rightFork] = false;
    }

    public synchronized void putDownForks(int philosopher) {
        int leftFork = philosopher;
        int rightFork = (philosopher + 1) % forks.length;

        // Libera ambos tenedores
        forks[leftFork] = true;
        forks[rightFork] = true;

        // Notifica a todos los filósofos para que revisen si pueden tomar los tenedores
        notifyAll();
    }
}