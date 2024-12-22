package practica4.ejercicio9;

public class Lamport {
    /* El número de procesos */
    static final int NUM_PROCESOS = 2;
    
    /* Arreglo para los números de solicitud de cada proceso */
    static volatile int[] numero = new int[NUM_PROCESOS];
    
    /* Arreglo para las banderas de cada proceso (si está solicitando la sección crítica) */
    static volatile boolean[] peticion = new boolean[NUM_PROCESOS];
    
    /* Variable que mantiene el valor de la sección crítica */
    static volatile int inCS = 0;
    
    class P extends Thread {
        public void run() {
            for (int i = 0; i < 10000000; ++i) {
                // Pide acceso a la sección crítica
                peticion[0] = true;
                numero[0] = 1 + getMaxRequestNumber(); // Calcula el nuevo número de solicitud

                // Termina la solicitud
                peticion[0] = false;

                // Espera hasta que pueda entrar en la sección crítica
                for (int j = 0; j < NUM_PROCESOS; j++) {
                    if (j != 0) {
                        // Espera si el proceso j está pidiendo acceso o tiene un número de solicitud mayor
                        while (peticion[j] || (numero[0] < numero[j]) || (numero[0] == numero[j] && 0 < j)) {
                            Thread.yield(); // Deja que otros procesos usen la CPU
                        }
                    }
                }

                // Accede a la sección crítica
                inCS++;
                
                // Al salir de la sección crítica, resetea su número de solicitud
                numero[0] = 0;
            }
        }
    }
    
    class Q extends Thread {
        public void run() {
            for (int i = 0; i < 10000000; ++i) {
                // Pide acceso a la sección crítica
                peticion[1] = true;
                numero[1] = 1 + getMaxRequestNumber(); // Calcula el nuevo número de solicitud

                // Termina la solicitud
                peticion[1] = false;

                // Espera hasta que pueda entrar en la sección crítica
                for (int j = 0; j < NUM_PROCESOS; j++) {
                    if (j != 1) {
                        // Espera si el proceso j está pidiendo acceso o tiene un número de solicitud mayor
                        while (peticion[j] || (numero[1] < numero[j]) || (numero[1] == numero[j] && 1 < j)) {
                            Thread.yield(); // Deja que otros procesos usen la CPU
                        }
                    }
                }

                // Accede a la sección crítica
                inCS--;
                
                // Al salir de la sección crítica, resetea su número de solicitud
                numero[1] = 0;
            }
        }
    }

    // Método para obtener el máximo número de solicitud de todos los procesos
    private int getMaxRequestNumber() {
        int max = 0;
        for (int i = 0; i < NUM_PROCESOS; i++) {
            max = Math.max(max, numero[i]);
        }
        return max;
    }

    Lamport() {
        // Inicializa las solicitudes y números
        for (int i = 0; i < NUM_PROCESOS; i++) {
            peticion[i] = false;
            numero[i] = 0;
        }

        // Crea y empieza los procesos
        Thread p = new P();
        Thread q = new Q();
        p.start();
        q.start();
        
        try {
            p.join();
            q.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Imprime el resultado final de la sección crítica
        System.out.println(inCS);
    }

    public static void main(String[] args) {
        new Lamport();
    }
}
