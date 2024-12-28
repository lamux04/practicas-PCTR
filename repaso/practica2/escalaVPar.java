package practica2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class escalaVPar {
    public static void main(String[] args) {
        long resultado = 0;
        final int N = 100000;
        final int nHilos = 8;
        int[] vector = new int[N];

        ExecutorService executor = Executors.newFixedThreadPool(N);

        for (int i = 0; i < nHilos; ++i) {
            
        }
    }
}
