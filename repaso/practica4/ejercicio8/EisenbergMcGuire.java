package practica4.ejercicio8;

public class EisenbergMcGuire {
    static final int N = 2; // Número de procesos
    static volatile int inCS = 0; // Contador de procesos en la sección crítica
    static volatile int index = 0; // Índice compartido
    static volatile Estado[] indicator = new Estado[N]; // Indicadores de estado para cada proceso

    enum Estado {
        RESTO, QUIERE_ENTRAR, EN_SC
    }

    class Process extends Thread {
        private final int id;

        Process(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; ++i) {
                // Protocolo de entrada
                indicator[id] = Estado.QUIERE_ENTRAR;
                int j = (index + 1) % N;

                while (j != id) {
                    if (indicator[j] == Estado.EN_SC) {
                        j = (index + 1) % N;
                    } else {
                        j = (j + 1) % N;
                    }
                }

                indicator[id] = Estado.EN_SC;
                j = 0;
                while (j < N && (j == id || indicator[j] != Estado.EN_SC)) {
                    j++;
                }

                while (!(j >= N && (index == id || indicator[index] == Estado.RESTO))) {
                    j = 0;
                    while (j < N && (j == id || indicator[j] != Estado.EN_SC)) {
                        j++;
                    }
                }

                index = id;

                // Sección crítica
                inCS++;
                inCS--;

                // Protocolo de salida
                indicator[id] = Estado.RESTO;
                index = (id + 1) % N;

                // Resto
            }
        }
    }

    EisenbergMcGuire() {
        Thread[] processes = new Thread[N];
        for (int i = 0; i < N; i++) {
            indicator[i] = Estado.RESTO; // Inicializar estados
            processes[i] = new Process(i);
        }

        for (Thread process : processes) {
            process.start();
        }

        for (Thread process : processes) {
            try {
                process.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("inCS: " + inCS);
    }

    public static void main(String[] args) {
        new EisenbergMcGuire();
    }
}
