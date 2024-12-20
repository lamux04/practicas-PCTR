package trabajo;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase para simular la propagación de una onda en paralelo utilizando hilos.
 */
public class SimulacionParalela {

    /**
     * Realiza la simulación de una onda en paralelo.
     *
     * @param puntos_espacio número de puntos en el espacio (debe ser mayor que 2).
     * @param puntos_tiempo número de pasos de tiempo (debe ser mayor que 0).
     * @param inicio_pulso índice de inicio del pulso inicial (debe estar en el rango [0, puntos_espacio)).
     * @param fin_pulso índice de fin del pulso inicial (debe estar en el rango (inicio_pulso, puntos_espacio)).
     * @param nHilos número de hilos a utilizar (debe ser mayor que 0).
     * @return el tiempo que tomó ejecutar la simulación en segundos.
     * 
     * @throws IllegalArgumentException si los parámetros no cumplen con las restricciones necesarias.
     */
    public static double simular(int puntos_espacio, int puntos_tiempo, int inicio_pulso, int fin_pulso, int nHilos) {
        // ------------------------ VALIDACIÓN DE PRECONDICIONES -------------------------
        if (puntos_espacio <= 2) {
            throw new IllegalArgumentException("El número de puntos en el espacio debe ser mayor que 2.");
        }
        if (puntos_tiempo <= 0) {
            throw new IllegalArgumentException("El número de pasos de tiempo debe ser mayor que 0.");
        }
        if (inicio_pulso < 0 || inicio_pulso >= puntos_espacio) {
            throw new IllegalArgumentException("El índice de inicio del pulso debe estar en el rango [0, puntos_espacio).");
        }
        if (fin_pulso <= inicio_pulso || fin_pulso >= puntos_espacio) {
            throw new IllegalArgumentException("El índice de fin del pulso debe estar en el rango (inicio_pulso, puntos_espacio).");
        }
        if (nHilos <= 0) {
            throw new IllegalArgumentException("El número de hilos debe ser mayor que 0.");
        }

        // --------------- DEFINCIÓN DE PARÁMETROS DE ENTRADA -----------------
        final double L = 10.0; // Longitud del dominio (espacio)
        final double T = 10.0; // Tiempo total de la simulación
        final double c = 1.0;  // Velocidad de propagación de la onda
        final int nx = puntos_espacio;    // Número de puntos en el espacio
        final int nt = puntos_tiempo;    // Número de pasos de tiempo

        // Paso espacial y temporal
        final double dx = L / (nx - 1); // Separación entre puntos en el espacio
        final double dt = T / nt;      // Duración de cada paso de tiempo

        // Condición de estabilidad de Courant
        if (c * dt / dx > 1) {
            throw new IllegalArgumentException("Condición de Courant violada. Ajusta dt o dx.");
        }

        // ------------------ INICIALIZACIÓN VARIABLES PARA SIMULACIÓN --------------
        double[] u = new double[nx];       // Amplitud en el tiempo actual
        double[] uPast = new double[nx];  // Amplitud en el tiempo pasado
        double[] uFuture = new double[nx];// Amplitud en el tiempo futuro

        for (int i = 0; i < nx; i++) {
            if (i >= inicio_pulso && i < fin_pulso) {
                u[i] = 1.0; // Amplitud del pulso
            } else {
                u[i] = 0.0; // Amplitud inicial fuera del pulso
            }
        }

        // Copiar el estado inicial al pasado
        System.arraycopy(u, 0, uPast, 0, nx);

        // ---------------------- SIMULACIÓN EN PARALELO -------------------------
        double courantFactor = Math.pow(c * dt / dx, 2); // Factor Courant

        CyclicBarrier barrera = new CyclicBarrier(nHilos);
        AtomicInteger nHilosTerminados = new AtomicInteger(0);
        ExecutorService executor = Executors.newFixedThreadPool(nHilos);

        double inicioSimulacion = System.nanoTime();

        for (int i = 0; i < nHilos; i++) {
            int inicio = i * (nt / nHilos);
            int fin = (i + 1) * (nt / nHilos);
            if (i == nHilos - 1) {
                fin = nt; // Último hilo llega al final
            }
            executor.submit(new SimulacionParcial(uFuture, u, uPast, barrera, nt, inicio, fin, nx, nHilos, nHilosTerminados, courantFactor));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            throw new RuntimeException("La simulación fue interrumpida.", e);
        }

        double finSimulacion = System.nanoTime();
        return (finSimulacion - inicioSimulacion) / 1e9;
    }
}

/**
 * Clase que representa una tarea de simulación parcial para un hilo.
 */
class SimulacionParcial implements Runnable {
    private final double[] uFuture, u, uPast;
    private final CyclicBarrier barrera;
    private final int nt, inicio, fin, nx, nHilos;
    private final AtomicInteger nHilosTerminados;
    private final double courantFactor;

    public SimulacionParcial(double[] uFuture, double[] u, double[] uPast, CyclicBarrier barrera, int nt, int inicio, int fin, int nx, int nHilos, AtomicInteger nHilosTerminados, double courantFactor) {
        this.uFuture = uFuture;
        this.u = u;
        this.uPast = uPast;
        this.barrera = barrera;
        this.nt = nt;
        this.inicio = inicio;
        this.fin = fin;
        this.nx = nx;
        this.nHilos = nHilos;
        this.nHilosTerminados = nHilosTerminados;
        this.courantFactor = courantFactor;
    }

    @Override
    public void run() {
        for (int t = 0; t < nt; t++) {
            // Actualizar las amplitudes en el intervalo asignado al hilo
            for (int i = inicio; i < fin; i++) {
                if (i > 0 && i < nx - 1) {
                    uFuture[i] = 2 * u[i] - uPast[i] + courantFactor * (u[i + 1] - 2 * u[i] + u[i - 1]);
                }
            }

            if (nHilosTerminados.incrementAndGet() == nHilos) {
                System.arraycopy(u, 0, uPast, 0, nx);
                System.arraycopy(uFuture, 0, u, 0, nx);
                nHilosTerminados.set(0);
            }

            try {
                barrera.await();
            } catch (Exception e) {
                throw new RuntimeException("Error en la sincronización del hilo.", e);
            }
        }
    }
}
