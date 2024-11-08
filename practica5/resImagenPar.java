import java.util.Random;

public class resImagenPar 
{
    public static void main(String[] args) 
    {
        final int k = 10000;
        final int nhilos = 8;
        int[][] matriz = new int[k][k];
        Thread[] hilos = new Thread[nhilos];

        Random random = new Random();

        for (int i = 0; i < k; ++i)
        for (int j = 0; j < k; ++j)
        {
            matriz[i][j] = random.nextInt() % 256;
        }

        double inicio = System.nanoTime();

        for (int i = 0; i < nhilos; ++i)
        {
            hilos[i] = new Thread(new resImagenParcial(matriz, i, k, nhilos));
            hilos[i].start();
        }

        for (int i = 0; i < nhilos; ++i)
        {
            try {
                hilos[i].join();
            } catch (Exception e) {
            }
        }
        
        double fin = System.nanoTime();
        
        System.out.println((fin - inicio) / Math.pow(10, 9));
    }
}

class resImagenParcial implements Runnable
{
    int[][] matriz;
    int id;
    int n;
    int nhilos;

    public resImagenParcial (int[][] matriz, int id, int n, int nhilos)
    {
        this.matriz = matriz;
        this.id = id;
        this.n = n;
        this.nhilos = nhilos;
    }

    public void run ()
    {
        for (int i = id + 1; i < n - 1; i += nhilos)
        {
            for (int j = 1; j < n - 1; ++j) 
            {
                matriz[i][j] = (4 * matriz[i][j] - matriz[i + 1][j] - matriz[i][j + 1] - matriz[i - 1][j]
                        - matriz[i][j - 1]) / 8;
            }
        }
    }
}