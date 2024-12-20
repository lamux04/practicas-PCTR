package trabajo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Pruebas {
     public static void escribirArchivo(String nombreArchivo, String[] datos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (String linea : datos) {
                writer.write(linea);
                writer.newLine();
            }
            System.out.println("Archivo creado con éxito: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        int[][] conjunto_pruebas = {
            {100, 200, 5, 6},
            {1000, 10000, 400, 600},
            {10000, 20000, 300, 400},
            {10000, 20000, 3, 4},
            {100000, 200000, 33, 400},
            {1000, 2000, 900, 1000}
        };

        int[] numHilos = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
        double[] tiempo_hilos = new double[numHilos.length];
        int imejorNhilos = 0, mejorNHilos;

        String[] datos = new String[numHilos.length + 1];

        // Calculamos el tiempo al cambiar de hilo con la prueba 4
        for (int i = 0; i < numHilos.length; ++i)
        {
            tiempo_hilos[i] = SimulacionParalela.simular(conjunto_pruebas[4][0], conjunto_pruebas[4][1],
                    conjunto_pruebas[4][2], conjunto_pruebas[4][3], numHilos[i]);
            if (tiempo_hilos[i] < tiempo_hilos[imejorNhilos])
                imejorNhilos = i;
        }

        mejorNHilos = numHilos[imejorNhilos];
        

        // Calculamos el tiempo entre el secuencial
        double tiempo_secuencial = SimulacionOnda.simular(conjunto_pruebas[4][0], conjunto_pruebas[4][1],
                conjunto_pruebas[4][2], conjunto_pruebas[4][3]);

        // Calculamos speedups
        datos[0] = "Hilos    Tiempo    Speedup";
        for (int i = 1; i < numHilos.length + 1; ++i)
        {
            datos[i] = "" + numHilos[i - 1] + "    " + tiempo_hilos[i - 1] + "    " + tiempo_secuencial / tiempo_hilos[i - 1];
        }
        
        // Escribir los datos en un archivo
        escribirArchivo("trabajo/datos_tiempo.txt", datos);
    }
}
