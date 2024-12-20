package trabajo;

/**
 * Clase para simular la propagación de una onda en una dimensión usando diferencias finitas.
 */
public class SimulacionOnda {

    /**
     * Simula la propagación de una onda y devuelve el tiempo de simulación en segundos.
     * 
     * @param puntos_espacio número de puntos en el espacio (debe ser mayor que 2).
     * @param puntos_tiempo número de pasos de tiempo (debe ser mayor que 0).
     * @param inicio_pulso índice de inicio del pulso inicial (debe ser menor que fin_pulso y estar en el rango [0, puntos_espacio)).
     * @param fin_pulso índice de fin del pulso inicial (debe estar en el rango [1, puntos_espacio)).
     * @return el tiempo que tomó ejecutar la simulación en segundos.
     * 
     * @throws IllegalArgumentException si los parámetros no cumplen con las restricciones necesarias.
     */
    public static double simular(int puntos_espacio, int puntos_tiempo, int inicio_pulso, int fin_pulso) {
        // ------------------------ VALIDACIÓN DE PRECONDICIONES -------------------------
        if (puntos_espacio <= 2) {
            throw new IllegalArgumentException("El número de puntos en el espacio debe ser mayor que 2.");
        }
        if (puntos_tiempo <= 0) {
            throw new IllegalArgumentException("El número de puntos en el tiempo debe ser mayor que 0.");
        }
        if (inicio_pulso < 0 || inicio_pulso >= puntos_espacio) {
            throw new IllegalArgumentException("El índice de inicio del pulso debe estar en el rango [0, puntos_espacio).");
        }
        if (fin_pulso <= inicio_pulso || fin_pulso >= puntos_espacio) {
            throw new IllegalArgumentException("El índice de fin del pulso debe estar en el rango (inicio_pulso, puntos_espacio).");
        }

        // --------------- DEFINCIÓN DE PARÁMETROS DE ENTRADA -----------------
        final double L = 10.0; // Longitud del dominio (espacio)
        final double T = 10.0; // Tiempo total de la simulación
        final double c = 1.0;  // Velocidad de propagación de la onda
        final int nx = puntos_espacio; // Número de puntos en el espacio
        final int nt = puntos_tiempo;  // Número de pasos de tiempo

        // Paso espacial y temporal
        final double dx = L / (nx - 1); // Separación entre puntos en el espacio
        final double dt = T / nt;      // Duración de cada paso de tiempo

        // Condición de estabilidad de Courant
        if (c * dt / dx > 1) {
            throw new IllegalArgumentException("Condición de estabilidad de Courant violada. Ajusta puntos_espacio o puntos_tiempo.");
        }

        // ------------------ INICIALIZACIÓN VARIABLES PARA SIMULACIÓN --------------
        // Inicializar las variables necesarias para la simulación
        double[] u = new double[nx];       // Amplitud en el tiempo actual
        double[] uPast = new double[nx];  // Amplitud en el tiempo pasado
        double[] uFuture = new double[nx];// Amplitud en el tiempo futuro

        // Configurar las condiciones iniciales
        for (int i = 0; i < nx; i++) {
            if (i >= inicio_pulso && i < fin_pulso) {
                u[i] = 1.0; // Amplitud del pulso
            } else {
                u[i] = 0.0; // Amplitud inicial fuera del pulso
            }
        }

        // Copiar estado inicial al estado pasado
        System.arraycopy(u, 0, uPast, 0, nx);

        // ---------------------- SIMULACIÓN DE LA ONDA -------------------------
        double courantFactor = Math.pow(c * dt / dx, 2); // Factor Courant

        double inicio = System.nanoTime();

        for (int t = 0; t < nt; t++) {
            // Actualizar los valores de amplitud para el siguiente paso de tiempo
            for (int i = 1; i < nx - 1; i++) { // Evitamos los extremos
                uFuture[i] = 2 * u[i] - uPast[i] +
                             courantFactor * (u[i + 1] - 2 * u[i] + u[i - 1]);
            }

            // Actualizar los estados para el siguiente paso de tiempo
            System.arraycopy(u, 0, uPast, 0, nx); // u -> uPast
            System.arraycopy(uFuture, 0, u, 0, nx); // uFuture -> u
        }

        double fin = System.nanoTime();

        // Devolver el tiempo de ejecución en segundos
        return (fin - inicio) / 1e9;
    }
}
