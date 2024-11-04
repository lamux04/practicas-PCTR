import java.util.Random;

public class matVectorSecuencial 
{
    public static void main(String[] args) 
    {
        int[][] matriz1 = new int[(int) Math.pow(10, 3)][(int) Math.pow(10, 3)];
        int[][] matriz2 = new int[(int) Math.pow(10, 3)][(int) Math.pow(10, 3)];
        int[][] matriz3 = new int[(int) Math.pow(10, 3)][(int) Math.pow(10, 3)];
        int nhilos = 1024;
        Random random = new Random();

        for (int i = 0; i < Math.pow(10, 3); ++i)
        {
            for (int j = 0; j < Math.pow(10, 3); ++j) {
                matriz1[i][j] = random.nextInt();
                matriz2[i][j] = random.nextInt();
            }
        }

        Thread[] hilos = new Thread[nhilos];

        double inicio = System.nanoTime();

        for (int i = 0; i < nhilos; ++i)
        {
            hilos[i] = new Thread(new matVectorParcial(matriz1, matriz2, matriz3, i, nhilos));
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

class matVectorParcial implements Runnable
{
    int id;
    int nhebras;
    int[][] matriz1, matriz2, matriz3;

    public matVectorParcial(int[][] matriz1, int[][] matriz2, int[][] matriz3, int id, int nhebras)
    {
        this.id = id;
        this.matriz1 = matriz1;
        this.matriz2 = matriz2;
        this.nhebras = nhebras;
        this.matriz3 = matriz3;
    }

    public void run ()
    {
        for (int k = 0; k < Math.pow(10, 3); k += nhebras)
        for (int i = id; i < Math.pow(10, 3); i += nhebras)
        {
            int valor = 0;
            for (int j = 0; j < Math.pow(10, 3); j++)
            {
                valor += matriz1[i][j] * matriz2[i][k];
            }
            matriz3[k][i] = valor;
        }
    }

}