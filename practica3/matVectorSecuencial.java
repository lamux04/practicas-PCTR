import java.util.Random;

public class matVectorSecuencial 
{
    public static void main(String[] args) 
    {
        int[][] matriz = new int[(int) Math.pow(10, 3)][(int) Math.pow(10, 3)];
        int[] vector = new int[(int) Math.pow(10, 3)];
        int[] resultado = new int[(int) Math.pow(10, 3)];
        int nhilos = 2;
        Random random = new Random();

        for (int i = 0; i < Math.pow(10, 3); ++i)
        {
            vector[i] = random.nextInt();
            for (int j = 0; j < Math.pow(10, 3); ++j) {
                matriz[i][j] = random.nextInt();
            }
        }

        Thread[] hilos = new Thread[nhilos];

        for (int i = 0; i < nhilos; ++i)
        {
            hilos[i] = new Thread(new matVectorParcial(matriz, vector, resultado, i, nhilos));
            hilos[i].start();
        }

        for (int i = 0; i < nhilos; ++i)
        {
            try {
                hilos[i].join();
            } catch (Exception e) {
            }
        }
        
        for (int i = 0; i < Math.pow(10, 3); ++i)
        {
            System.out.println(resultado[i]);
        }
    }
}

class matVectorParcial implements Runnable
{
    int id;
    int nhebras;
    int[][] matriz;
    int[] vector;
    int[] resultado;

    public matVectorParcial(int[][] matriz, int[] vector, int[] resultado, int id, int nhebras)
    {
        this.id = id;
        this.matriz = matriz;
        this.vector = vector;
        this.nhebras = nhebras;
        this.resultado = resultado;
    }

    public void run ()
    {
        for (int i = id; i < Math.pow(10, 3); i += nhebras)
        {
            int valor = 0;
            for (int j = 0; j < Math.pow(10, 3); j++)
            {
                valor += matriz[i][j] * vector[i];
            }
            resultado[i] = valor;
        }
    }

}