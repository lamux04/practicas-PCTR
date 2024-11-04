import java.util.Random;
public class resImagen 
{
    public static void main(String[] args) 
    {
        final int k = 10000;
        int[][] matriz = new int[k][k];

        Random random = new Random();

        for (int i = 0; i < k; ++i)
        for (int j = 0; j < k; ++j)
        {
            matriz[i][j] = random.nextInt() % 256;
        }

        double inicio = System.nanoTime();

        for (int i = 1; i < k - 1; ++i)
        {
            for (int j = 1; j < k - 1; ++j) 
            {
                matriz[i][j] = (4 * matriz[i][j] - matriz[i + 1][j] - matriz[i][j + 1] - matriz[i - 1][j]
                        - matriz[i][j - 1]) / 8;
            }
        }
        
        double fin = System.nanoTime();
        
        System.out.println((fin - inicio) / Math.pow(10, 9));
    }
}
