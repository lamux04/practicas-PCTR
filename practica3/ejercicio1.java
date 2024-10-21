import java.util.Arrays;
import java.util.Random;

public class ejercicio1 {
    public static void main(String[] args) {
        // Definir el tamaño del vector
        int size = 1000;
        double[] vector = new double[size];
        
        // Generar números reales aleatorios y llenar el vector
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            vector[i] = random.nextDouble() * 1000; // Genera números entre 0 y 1000
        }

        // Medir el tiempo antes de la ordenación
        long startTime = System.nanoTime();

        // Ordenar el vector usando Arrays.sort()
        Arrays.sort(vector);

        // Medir el tiempo después de la ordenación
        long endTime = System.nanoTime();

        // Calcular la duración
        long duration = endTime - startTime;

        // Mostrar el tiempo de ejecución en nanosegundos
        System.out.println("Tiempo de ejecución para ordenar el vector: " + duration + " nanosegundos");

        // (Opcional) Mostrar el vector ordenado
        // System.out.println(Arrays.toString(vector));
    }
}
