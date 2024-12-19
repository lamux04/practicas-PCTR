package trabajo;



public class SimulacionOnda {

    // Método auxiliar para imprimir el estado actual de la onda
    private static void imprimirEstado(double[] u, int nx, int t, double dt) {
        System.out.println("Tiempo: " + (t * dt)); // Imprimir tiempo actual
        for (int i = 0; i < nx; i++) {
            System.out.print(String.format("%.2f ", u[i])); // Imprimir amplitud
        }
        System.out.println(); // Nueva línea después de imprimir el estado
    }

    public static void main(String[] args) {
        // --------------- DEFINCIÓN DE PARÁMETROS DE ENTRADA -----------------
        final double L = 10.0; // Longitud del dominio (espacio)
        final double T = 10.0; // Tiempo total de la simulación
        final double c = 1.0;  // Velocidad de propagación de la onda
        final int nx = 100;    // Número de puntos en el espacio
        final int nt = 500;    // Número de pasos de tiempo

        // Definir un pulso inicial (ejemplo: pulso rectangular en el centro)
        int inicioPulso = nx / 4;
        int finPulso = nx / 2;

        // Paso espacial y temporal
        final double dx = L / (nx - 1); // Separación entre puntos en el espacio
        final double dt = T / nt;      // Duración de cada paso de tiempo

        // Condición de estabilidad de Courant
        if (c * dt / dx > 1) {
            throw new IllegalArgumentException("Condición de Courant violada. Ajusta dt o dx.");
        }

        // ------------------ INICIALIZACIÓN VARIABLES PARA SIMULACIÓN --------------
        // Inicializar las variables necesarias para la simulación
        double[] u = new double[nx];       // Amplitud en el tiempo actual
        double[] uPast = new double[nx];  // Amplitud en el tiempo pasado
        double[] uFuture = new double[nx];// Amplitud en el tiempo futuro

        for (int i = 0; i < nx; i++) {
            if (i >= inicioPulso && i < finPulso) {
                u[i] = 1.0; // Amplitud del pulso
            } else {
                u[i] = 0.0; // Amplitud inicial fuera del pulso
            }
        }

        // Inicializar el estado pasado con el estado actual
        System.arraycopy(u, 0, uPast, 0, nx);

        // ---------------------- SIMULACIÓN ONDA -------------------------
        double courantFactor = Math.pow(c * dt / dx, 2); // Factor Courant

        for (int t = 0; t < nt; t++) {
            // Actualizar los valores de amplitud para el siguiente paso de tiempo
            for (int i = 1; i < nx - 1; i++) { // Evitamos los extremos
                uFuture[i] = 2 * u[i] - uPast[i] + 
                            courantFactor * (u[i + 1] - 2 * u[i] + u[i - 1]);
            }

            // Actualizar las matrices para el siguiente paso de tiempo
            System.arraycopy(u, 0, uPast, 0, nx);    // u -> uPast
            System.arraycopy(uFuture, 0, u, 0, nx); // uFuture -> u

            // Opción: imprimir en consola el estado de la onda (cada ciertos pasos)
            if (t % (nt / 100) == 0) { // Imprimir cada 10% de la simulación
                GraficaOnda.mostrarGrafica(u);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e)
                {}
            }
        }
    }
}


